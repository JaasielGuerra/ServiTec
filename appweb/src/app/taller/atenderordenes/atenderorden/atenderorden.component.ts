import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import { ToastrService } from "ngx-toastr";
import { InventarioRepuesto } from "../../../areatecnica/model/Inventario";
import { ServicioTecnico } from "../../../areatecnica/model/ServicioTecnico";
import { InventarioService } from "../../../areatecnica/service/inventario.service";
import { ServiciostecnicosService } from "../../../areatecnica/service/serviciostecnicos.service";
import { FotosDiagnostico } from "../../model/FotosDiagnostico";
import { Orden } from "../../model/Orden";
import { RepuestoEmpleado } from "../../model/RepuestoEmpleado";
import { ServicioAplicado } from "../../model/ServicioAplicado";
import { FotosService } from "../../services/fotos.service";
import { OrdenesService } from "../../services/ordenes.service";
import { RepuestosService } from "../../services/repuestos.service";
import { ServiciosService } from "../../services/servicios.service";

@Component({
  selector: "app-atenderorden",
  templateUrl: "./atenderorden.component.html",
  styleUrls: ["./atenderorden.component.css"],
})
export class AtenderordenComponent implements OnInit {
  id: string;
  ordenAtender: Orden;
  servicioNoAplicable: boolean;
  fotosDiagnostico: FotosDiagnostico[] = new Array();
  repuestosEmpleados: RepuestoEmpleado[] = new Array();
  serviciosAplicados: ServicioAplicado[] = new Array();

  modalClienteRef: NgbModalRef;
  modalFotoRef: NgbModalRef;
  modalServicioRef: NgbModalRef;
  modalRepuestoRef: NgbModalRef;

  serviciostecnicos: ServicioTecnico[];
  idServicioSel: number;
  servicioSel: ServicioTecnico;
  precioSel: number;
  cantidadServicioAdd: number;

  repuestos: InventarioRepuesto[];
  idRepuestoSel: number;
  repuestoSel: InventarioRepuesto;
  cantidadRepuestoAdd: number;

  selectedFile: File;
  descripcionFotoDiagnostico: string;

  constructor(
    private serMd: NgbModal,
    private activatedRoute: ActivatedRoute,
    private service: OrdenesService,
    private toastr: ToastrService,
    private rutas: Router,
    private serviciosSer: ServiciostecnicosService,
    private inventarioSer: InventarioService,
    private repuestoSer: RepuestosService,
    private serviciosAppSer: ServiciosService,
    private fotosSer: FotosService
  ) {}

  ngOnInit(): void {
    //obtener el id a editar
    this.id = this.activatedRoute.snapshot.params.id;

    this.service.atender(this.id).subscribe(
      (d) => {
        this.ordenAtender = d;
        this.servicioNoAplicable = this.ordenAtender.aplicable === 0;
        this.fotosSer
          .listarFotosDiagnostico(d.idOrden.toString())
          .subscribe((d) => {
            this.fotosDiagnostico = d;
            this.sumarTotalCostoServicio();
          });

        this.repuestoSer
          .listarRepuestosEmpleados(d.idOrden.toString())
          .subscribe((d) => {
            this.repuestosEmpleados = d;
            this.sumarTotalCostoServicio();
          });

        this.serviciosAppSer
          .listarServiciosAplicados(d.idOrden.toString())
          .subscribe((d) => {
            this.serviciosAplicados = d;
            this.sumarTotalCostoServicio();
          });
      },
      (error) => {
        console.log(error.message);

        this.rutas.navigate(["/notfound"]);
      }
    );
  }

  finalizar() {
    if (this.servicioNoAplicable) {
      if (
        confirm(
          "Esta orden de servicio se finalizará como servicio no aplicado. ¿Continuar?"
        )
      ) {
        this.service.finalizarOrden(this.ordenAtender).subscribe(
          (d) => {
            this.msjSuccess();
            this.rutas.navigate(["/atender-ordenes"]);
          },
          (error) => {
            this.msjError(error);
          }
        );
      }
    } else if (confirm("La orden va a pasar al área de cobro. ¿Continuar?")) {
      this.service.finalizarOrden(this.ordenAtender).subscribe(
        (d) => {
          this.msjSuccess();
          this.rutas.navigate(["/atender-ordenes"]);
        },
        (error) => {
          this.msjError(error);
        }
      );
    }
  }

  ponerPendiente() {
    if (
      confirm(
        "La orden se pondrá pendiente para continuar más tarde. ¿Continuar?"
      )
    ) {
      this.ordenAtender.aplicable = this.servicioNoAplicable ? 0 : 1;
      this.service.ponerPendiente(this.ordenAtender).subscribe(
        (d) => {
          this.rutas.navigate(["/atender-ordenes"]);
        },
        (error) => {
          this.msjError(error);
        }
      );
    }
  }

  detallesCliente(modal) {
    this.modalClienteRef = this.serMd.open(modal, { size: "sm" });
  }

  modalAgregarFoto(modal) {
    this.selectedFile = undefined;
    this.descripcionFotoDiagnostico = undefined;
    this.modalFotoRef = this.serMd.open(modal);
  }

  public onChangedFileFotoDiagnostico(event) {
    this.selectedFile = event.target.files[0];

    if (this.selectedFile.size > 65535) {
      this.selectedFile = undefined;
      alert("Tamaño de imagen superado, el máximo es de 64Kb");
      return;
    }
  }

  addFotosDiagnostico() {
    let foto = new FotosDiagnostico();
    foto.descripcion = this.descripcionFotoDiagnostico;

    this.fotosSer
      .addFoto(this.ordenAtender.idOrden.toString(), foto, this.selectedFile)
      .subscribe(
        (d) => {
          this.fotosDiagnostico.push(d);
          this.modalFotoRef.close();
        },
        (error) => {
          this.msjError(error);
        }
      );
  }

  eliminarFotoDiagnostico(id: string) {
    if (confirm("¿Está seguro?")) {
      this.fotosSer.eliminar(id).subscribe(
        (d) => {
          this.fotosDiagnostico.forEach((element, index) => {
            if (element.idFotosDiagnostico === Number(id)) {
              this.fotosDiagnostico.splice(index, 1);
            }
          });
        },
        (error) => {
          this.msjError(error);
        }
      );
    }
  }

  modalAgregarServicio(modal) {
    this.serviciosSer.getServiciosTecnitos("1").subscribe((d) => {
      this.serviciostecnicos = d;
      this.idServicioSel = d[0].idServicio;
      this.servicioSel = d[0];
      this.cantidadServicioAdd = undefined;
      this.precioSel = undefined;
      this.modalServicioRef = this.serMd.open(modal);
    });
  }

  onChangeSelectServicio() {
    this.serviciosSer
      .getServicioTecnito(this.idServicioSel.toString())
      .subscribe((d) => {
        this.servicioSel = d;
        this.precioSel = undefined;
      });
  }

  addServicio() {
    let servicio = new ServicioAplicado();
    servicio.precio = this.precioSel;
    servicio.cantidad = this.cantidadServicioAdd;
    servicio.servicio = this.servicioSel;

    this.serviciosAppSer
      .addServicioOrden(this.ordenAtender.idOrden.toString(), servicio)
      .subscribe(
        (d) => {
          this.serviciosAplicados.push(d);
          this.sumarTotalCostoServicio();
          this.modalServicioRef.close();
        },
        (error) => {
          this.msjError(error);
        }
      );
  }

  eliminarServicio(id: string) {
    if (confirm("¿Está seguro?")) {
      this.serviciosAppSer.eliminar(id).subscribe(
        (d) => {
          this.serviciosAplicados.forEach((element, index) => {
            if (element.idServicioAplicado === Number(id)) {
              this.serviciosAplicados.splice(index, 1);
            }
          });
          this.sumarTotalCostoServicio();
        },
        (error) => {
          this.msjError(error);
        }
      );
    }
  }

  modalAgregarRepuesto(modal) {
    this.inventarioSer.listar("0", "0", "0").subscribe((d) => {
      this.repuestos = d;
      this.idRepuestoSel = d[0].idInventarioRepuesto;
      this.repuestoSel = d[0];
      this.cantidadRepuestoAdd = undefined;
      this.modalRepuestoRef = this.serMd.open(modal);
    });
  }

  onChangeSelectRepuesto() {
    this.inventarioSer.obtener(this.idRepuestoSel.toString()).subscribe((d) => {
      this.repuestoSel = d;
    });
  }

  addRepuesto() {
    let repuesto = new RepuestoEmpleado();
    repuesto.precio = this.repuestoSel.precio;
    repuesto.cantidad = this.cantidadRepuestoAdd;
    repuesto.inventarioRepuesto = this.repuestoSel;

    this.repuestoSer
      .addRepuestoOrden(this.ordenAtender.idOrden.toString(), repuesto)
      .subscribe(
        (d) => {
          this.repuestosEmpleados.push(d);
          this.sumarTotalCostoServicio();
          this.modalRepuestoRef.close();
        },
        (error) => {
          this.msjError(error);
        }
      );
  }

  eliminarRepuesto(id: string) {
    if (confirm("¿Está seguro?")) {
      this.repuestoSer.eliminar(id).subscribe(
        (d) => {
          this.repuestosEmpleados.forEach((element, index) => {
            if (element.idRepuestoEmpleado === Number(id)) {
              this.repuestosEmpleados.splice(index, 1);
            }
          });
          this.sumarTotalCostoServicio();
        },
        (error) => {
          this.msjError(error);
        }
      );
    }
  }

  sumarTotalCostoServicio() {
    this.ordenAtender.costoServicios = this.serviciosAplicados
      .map((s) => s.precio * s.cantidad)
      .reduce((a, b) => a + b, 0);

    this.ordenAtender.costoRepuestos = this.repuestosEmpleados
      .map((r) => r.precio * r.cantidad)
      .reduce((a, b) => a + b, 0);
    this.ordenAtender.totalCostoServicio =
      this.ordenAtender.costoExtra +
      this.ordenAtender.costoServicios +
      this.ordenAtender.costoRepuestos;
  }

  msjSuccess() {
    this.toastr.success(
      '<span class="now-ui-icons ui-1_bell-53"></span> Realizado con éxito',
      "Éxito!",
      {
        closeButton: true,
        enableHtml: true,
        toastClass: "alert alert-success alert-with-icon",
        positionClass: "toast-top-right",
      }
    );
  }

  msjError(error) {
    this.toastr.error(
      '<span class="now-ui-icons ui-1_bell-53"></span> Descripción: ' +
        error.message,
      "Error",
      {
        closeButton: true,
        enableHtml: true,
        toastClass: "alert alert-danger alert-with-icon",
        positionClass: "toast-top-right",
      }
    );
  }
}
