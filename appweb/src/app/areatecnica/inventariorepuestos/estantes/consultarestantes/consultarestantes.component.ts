import { Component, OnInit } from "@angular/core";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import { EstanteService } from "../../../service/estante.service";
import { Estante } from "../../../model/Estante";
import { ToastrService } from "ngx-toastr";
import { THIS_EXPR } from "@angular/compiler/src/output/output_ast";

@Component({
  selector: "app-consultarestantes",
  templateUrl: "./consultarestantes.component.html",
  styleUrls: ["./consultarestantes.component.css"],
})
export class ConsultarestantesComponent implements OnInit {
  estante: Estante;
  estantes: Estante[];
  modalRef: NgbModalRef;

  constructor(
    private modal: NgbModal,
    private service: EstanteService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.consultar();
  }

  nuevo(modal) {
    this.estante = new Estante();
    this.modalRef = this.modal.open(modal);
  }

  editar(entidad: Estante, modal) {
    this.estante = entidad;
    this.modalRef = this.modal.open(modal);
  }

  guardar() {
    if (this.estante.idEstante) {
      this.service.actualizar(this.estante).subscribe(
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

    this.service.crear(this.estante).subscribe(
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
        this.estantes = data;
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
