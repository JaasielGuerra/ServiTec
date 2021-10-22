import { Component, OnInit } from "@angular/core";
import { ToastrService } from "ngx-toastr";
import { Tecnico } from "../../model/Tecnico";
import { TecnicosService } from "../../service/tecnicos.service";

@Component({
  selector: "app-consultarpersonal",
  templateUrl: "./consultarpersonal.component.html",
  styleUrls: ["./consultarpersonal.component.css"],
})
export class ConsultarpersonalComponent implements OnInit {
  tecnicos: Tecnico[];

  constructor(
    private service: TecnicosService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.consultar();
  }
  consultar() {
    this.service.listarTodos().subscribe(
      (d) => {
        this.tecnicos = d;
      },
      (e) => {
        alert("Error consultando: " + e.message);
      }
    );
  }

  desactivar(id: string) {
    if (!confirm("¿Está seguro de desactivarlo?")) {
      return;
    }

    this.service.cambiarEstado(id, 0).subscribe(
      (d) => {
        this.msjSuccess();
        this.consultar();
      },
      (e) => {
        this.msjError(e);
      }
    );
  }

  activar(id: string) {
    this.service.cambiarEstado(id, 1).subscribe(
      (d) => {
        this.msjSuccess();
        this.consultar();
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
