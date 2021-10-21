import { Component, OnInit } from "@angular/core";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import { ToastrService } from "ngx-toastr";
import { Ubicacion } from "../../../model/Ubicacion";
import { UbicacionService } from "../../../service/ubicacion.service";

@Component({
  selector: "app-consultarubicaciones",
  templateUrl: "./consultarubicaciones.component.html",
  styleUrls: ["./consultarubicaciones.component.css"],
})
export class ConsultarubicacionesComponent implements OnInit {

  ubicacion: Ubicacion;
  ubicaciones: Ubicacion[];
  modalRef: NgbModalRef;

  constructor(
    private modal: NgbModal,
    private service: UbicacionService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.consultar();
  }

  nuevo(modal) {
    this.ubicacion = new Ubicacion();
    this.modalRef = this.modal.open(modal);
  }

  editar(entidad: Ubicacion, modal) {
    this.ubicacion = entidad;
    this.modalRef = this.modal.open(modal);
  }

  guardar() {
    if (this.ubicacion.idUbicacion) {

      this.service.actualizar(this.ubicacion).subscribe(
        (data) => {
          this.consultar();
          this.msjSuccess();
          this.modalRef.close();
        },
        (error) => {
          this.msjError(error);
        }
      );
      return;
    }

    this.service.crear(this.ubicacion).subscribe(
      (data) => {
        this.consultar();
        this.msjSuccess();
        this.modalRef.close();
      },
      (error) => {
        this.msjError(error);
      }
    );
  }

  consultar() {
    this.service.listar("1").subscribe(
      (data) => {
        this.ubicaciones = data;
      },
      (error) => {
        this.msjError(error);
      }
    );
  }

  eliminar(id: string) {
    if (!confirm("¿Está seguro de eliminarlo?")) return;

    this.service.eliminar(id).subscribe(
      (d) => {
        this.consultar();
        this.msjSuccess();
      },
      (e) => {
        this.msjError(e);
      }
    );
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
