import { Component, OnInit } from "@angular/core";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import { ToastrService } from "ngx-toastr";
import { MotivoOrden } from "../../model/MotivoOrden";
import { MotivosordenService } from "../../services/motivosorden.service";

@Component({
  selector: "app-catalogomotivos",
  templateUrl: "./catalogomotivos.component.html",
  styleUrls: ["./catalogomotivos.component.css"],
})
export class CatalogomotivosComponent implements OnInit {
  motivos: MotivoOrden[];
  motivo: MotivoOrden;

  modalRef: NgbModalRef;

  constructor(
    private serviceMod: NgbModal,
    private service: MotivosordenService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.consultar();
  }

  nuevoMotivo(modal) {
    this.motivo = new MotivoOrden();
    this.modalRef = this.serviceMod.open(modal);
  }

  editar(entidad: MotivoOrden, modal) {
    this.motivo = entidad;
    this.modalRef = this.serviceMod.open(modal);
  }

  guardar() {
    if (this.motivo.idMotivoOrden) {
      this.service.actualizar(this.motivo).subscribe(
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

    this.service.crear(this.motivo).subscribe(
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

  consultar() {
    this.service.consultar().subscribe(
      (d) => {
        this.motivos = d;
      },
      (error) => {
        this.msjError(error);
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
