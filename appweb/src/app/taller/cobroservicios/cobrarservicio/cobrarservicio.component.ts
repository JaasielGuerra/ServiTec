import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import moment from "moment";
import { ToastrService } from "ngx-toastr";
import { FotosDiagnostico } from "../../model/FotosDiagnostico";
import { Orden } from "../../model/Orden";
import { RepuestoEmpleado } from "../../model/RepuestoEmpleado";
import { ServicioAplicado } from "../../model/ServicioAplicado";
import { FotosService } from "../../services/fotos.service";
import { OrdenesService } from "../../services/ordenes.service";
import { RepuestosService } from "../../services/repuestos.service";
import { ServiciosService } from "../../services/servicios.service";

@Component({
  selector: "app-cobrarservicio",
  templateUrl: "./cobrarservicio.component.html",
  styleUrls: ["./cobrarservicio.component.css"],
})
export class CobrarservicioComponent implements OnInit {
  id: string;
  ordenCobrar: Orden;
  fotosDiagnostico: FotosDiagnostico[] = new Array();
  repuestosEmpleados: RepuestoEmpleado[] = new Array();
  serviciosAplicados: ServicioAplicado[] = new Array();

  efectivo: number;
  cambio: number;

  modalFotosRef: NgbModalRef;
  modalRepuestosRef: NgbModalRef;
  modalServiciosRef: NgbModalRef;

  constructor(
    private serMd: NgbModal,
    private activatedRoute: ActivatedRoute,
    private repuestoSer: RepuestosService,
    private serviciosAppSer: ServiciosService,
    private fotosSer: FotosService,
    private service: OrdenesService,
    private rutas: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    //obtener el id
    this.id = this.activatedRoute.snapshot.params.id;

    this.service.obtener(this.id).subscribe(
      (d) => {
        this.ordenCobrar = d;
        this.ordenCobrar.fechaEntrega = moment().format("YYYY-MM-DD");
      },
      (error) => {
        console.log(error.message);

        this.rutas.navigate(["/notfound"]);
      }
    );
  }

  cobrar() {
    if (confirm("Realizar el cobro del servicio?")) {
      this.service.cobrarOrden(this.ordenCobrar).subscribe(
        (d) => {
          this.msjSuccess();
          this.rutas.navigate(["/cobro-servicios"]);
        },
        (error) => {
          this.msjError(error);
        }
      );
    }
  }

  entregar() {
    if (confirm("¿Realizar entrega del servicio?")) {
      this.service.entregarOrden(this.ordenCobrar).subscribe(
        (d) => {
          this.msjSuccess();
          this.rutas.navigate(["/cobro-servicios"]);
        },
        (error) => {
          this.msjError(error);
        }
      );
    }
  }

  verFotosDiagnostico(modal) {
    this.fotosSer
      .listarFotosDiagnostico(this.ordenCobrar.idOrden.toString())
      .subscribe((d) => {
        this.fotosDiagnostico = d;
        this.modalFotosRef = this.serMd.open(modal, { size: "lg" });
      });
  }

  verRepuestosEmpleados(modal) {
    this.repuestoSer
      .listarRepuestosEmpleados(this.ordenCobrar.idOrden.toString())
      .subscribe((d) => {
        this.repuestosEmpleados = d;
        this.modalRepuestosRef = this.serMd.open(modal, { size: "lg" });
      });
  }

  verServiciosAplicados(modal) {
    this.serviciosAppSer
      .listarServiciosAplicados(this.ordenCobrar.idOrden.toString())
      .subscribe((d) => {
        this.serviciosAplicados = d;
        this.modalServiciosRef = this.serMd.open(modal, { size: "lg" });
      });
  }

  calcularCambio() {
    this.cambio = this.efectivo - this.ordenCobrar.totalCostoServicio;
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
