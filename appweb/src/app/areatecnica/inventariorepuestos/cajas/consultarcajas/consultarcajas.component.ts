import { Component, OnInit } from "@angular/core";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import { ToastrService } from "ngx-toastr";
import { CajaService } from "../../../service/caja.service";
import { Caja } from "../../../model/Caja";

@Component({
  selector: "app-consultarcajas",
  templateUrl: "./consultarcajas.component.html",
  styleUrls: ["./consultarcajas.component.css"],
})
export class ConsultarcajasComponent implements OnInit {
  caja: Caja;
  cajas: Caja[];
  modalRef: NgbModalRef;

  constructor(
    private modal: NgbModal,
    private service: CajaService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.consultar();
  }

  nuevo(modal) {
    this.caja = new Caja();
    this.modalRef = this.modal.open(modal);
  }

  editar(entidad: Caja, modal) {
    this.caja = entidad;
    this.modalRef = this.modal.open(modal);
  }

  guardar() {
    if (this.caja.idCaja) {
      this.service.actualizar(this.caja).subscribe(
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

    this.service.crear(this.caja).subscribe(
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
        this.cajas = data;
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
