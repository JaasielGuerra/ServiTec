import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import { ToastrService } from "ngx-toastr";
import { Tecnico } from "../../../areatecnica/model/Tecnico";
import { TecnicosService } from "../../../areatecnica/service/tecnicos.service";
import { Cliente } from "../../model/Cliente";
import { MotivoOrden } from "../../model/MotivoOrden";
import { Orden } from "../../model/Orden";
import { Prioridad } from "../../model/Prioridad";
import { ClientesService } from "../../services/clientes.service";
import { MotivosordenService } from "../../services/motivosorden.service";
import { OrdenesService } from "../../services/ordenes.service";
import { PrioridadesService } from "../../services/prioridades.service";

@Component({
  selector: "app-crearordenservicio",
  templateUrl: "./crearordenservicio.component.html",
  styleUrls: ["./crearordenservicio.component.css"],
})
export class CrearordenservicioComponent implements OnInit {
  ordenServicio: Orden = new Orden();

  selectedFile: File;
  url: any;
  modalImagenRef: NgbModalRef;
  msjImagen: string;

  disponibilidadTecnico: number;

  prioridades: Prioridad[];
  clientes: Cliente[];
  tenicos: Tecnico[];
  motivos: MotivoOrden[];

  constructor(
    private servicioModal: NgbModal,
    private service: OrdenesService,
    private tecnicoService: TecnicosService,
    private prioridadService: PrioridadesService,
    private clientesService: ClientesService,
    private motivosService: MotivosordenService,
    private toastr: ToastrService,
    private rutas: Router
  ) {}

  ngOnInit(): void {
    this.msjImagen = "Seleccione una imagen";
    this.ordenServicio.tecnico = new Tecnico();
    this.ordenServicio.motivoOrden = new MotivoOrden();
    this.ordenServicio.prioridad = new Prioridad();
    this.ordenServicio.cliente = new Cliente();
    this.ordenServicio.tecnico = new Tecnico();
    this.motivosService.consultar().subscribe((d) => {
      this.motivos = d;
    });
    this.prioridadService.consultar().subscribe((d) => {
      this.prioridades = d;
    });
    this.clientesService.consultar("1", "").subscribe((d) => {
      this.clientes = d;
    });
    this.tecnicoService.listar("1").subscribe((d) => {
      this.tenicos = d;
      this.ordenServicio.tecnico.idTecnico = this.tenicos[0].idTecnico;
    });
  }

  modalSeleccionarImg(modal) {
    this.modalImagenRef = this.servicioModal.open(modal);
  }

  public onFileChanged(event) {
    this.selectedFile = event.target.files[0];

    if (this.selectedFile.size > 65535) {
      this.selectedFile = undefined;
      alert("Tamaño de imagen superado, el máximo es de 64Kb");
      return;
    }
  }

  seleccionarImagen() {
    var reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);

    reader.onload = (_event) => {
      this.url = reader.result;
    };

    this.msjImagen = "Imagen seleccionada";
    this.modalImagenRef.close();
  }

  crearOrden() {
    if (!this.url) {
      alert("Debe seleccionar una imagen");
      return;
    }

    this.service
      .crearOrden(
        this.selectedFile,
        this.ordenServicio,
        this.ordenServicio.tecnico.idTecnico.toString(),
        this.ordenServicio.motivoOrden.idMotivoOrden.toString(),
        this.ordenServicio.prioridad.idPrioridad.toString(),
        "1",
        this.ordenServicio.cliente.idCliente.toString()
      )
      .subscribe(
        (d) => {
          this.rutas.navigate(["ordenes-servicio"]);
          this.msjSuccess();
        },
        (e) => {
          this.msjError(e);
        }
      );
  }

  verDispobilidadTecnico(modal) {
    this.tecnicoService
      .disponibilidad(this.ordenServicio.tecnico.idTecnico.toString())
      .subscribe(
        (d) => {
          this.disponibilidadTecnico = d;
        },
        (e) => {
          console.log("error al ver disponibilidad: " + e.message);
          return;
        }
      );

    this.tecnicoService
      .obtener(this.ordenServicio.tecnico.idTecnico.toString())
      .subscribe(
        (d) => {
          this.ordenServicio.tecnico = d;
        },
        (e) => {
          console.log("error al obtener tecnico: " + e.message);
          return;
        }
      );
    this.servicioModal.open(modal);
  }

  msjSuccess() {
    this.toastr.success(
      '<span class="now-ui-icons ui-1_bell-53"></span> Orden creada con éxito',
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
