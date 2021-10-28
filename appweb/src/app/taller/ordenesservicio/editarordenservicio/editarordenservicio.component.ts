import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
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
  selector: "app-editarordenservicio",
  templateUrl: "./editarordenservicio.component.html",
  styleUrls: ["./editarordenservicio.component.css"],
})
export class EditarordenservicioComponent implements OnInit {
  id: string;
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
    private activatedRoute: ActivatedRoute,
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
    //obtener el id a editar
    this.id = this.activatedRoute.snapshot.params.id;

    this.ordenServicio.tecnico = new Tecnico();
    this.ordenServicio.motivoOrden = new MotivoOrden();
    this.ordenServicio.prioridad = new Prioridad();
    this.ordenServicio.cliente = new Cliente();
    this.ordenServicio.tecnico = new Tecnico();

    this.service.obtener(this.id).subscribe(
      (d) => {
        this.ordenServicio = d;

        //convertir la imagen en base64 a File
        this.selectedFile = new File(
          [this.dataURItoBlob(this.ordenServicio.imagenReferencia)],
          "foto.jpg",
          { type: "image/jpg" }
        );

        this.cargarImagenUrl();

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
        });
      },
      (error) => {
        console.log(error.message);

        this.rutas.navigate(["/notfound"]);
      }
    );
  }

  actualizarOrden() {

    if (!this.url) {
      alert("Debe seleccionar una imagen");
      return;
    }

    this.service
    .actualizar(
      this.selectedFile,
      this.ordenServicio,
      this.ordenServicio.tecnico.idTecnico.toString(),
      this.ordenServicio.motivoOrden.idMotivoOrden.toString(),
      this.ordenServicio.prioridad.idPrioridad.toString(),
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
    this.cargarImagenUrl();
    this.modalImagenRef.close();
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
      '<span class="now-ui-icons ui-1_bell-53"></span> Orden actualizada con éxito',
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

  //convierte la imagen en bse64 a un Blod
  dataURItoBlob(dataURI) {
    const byteString = window.atob(dataURI);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: "image/png" });
    return blob;
  }
  cargarImagenUrl() {
    var reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);

    reader.onload = (_event) => {
      this.url = reader.result;
    };

    this.msjImagen = "Imagen seleccionada";
  }
}
