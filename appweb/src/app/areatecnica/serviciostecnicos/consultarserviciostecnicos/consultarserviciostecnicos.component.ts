import { Component, OnInit } from "@angular/core";
import { ToastrService } from "ngx-toastr";
import { ServicioTecnico } from "../../model/ServicioTecnico";
import { ServiciostecnicosService } from "../../service/serviciostecnicos.service";

@Component({
  selector: "app-consultarserviciostecnicos",
  templateUrl: "./consultarserviciostecnicos.component.html",
  styleUrls: ["./consultarserviciostecnicos.component.css"],
})
export class ConsultarserviciostecnicosComponent implements OnInit {
  servicios: ServicioTecnico[];

  constructor(
    private service: ServiciostecnicosService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.consultar();
  }

  eliminar(id: number) {
    if (confirm("¿Está seguro de eliminarlo?")) {
      this.service.eliminarServicioTenico(id.toString()).subscribe(
        (d) => {
          this.consultar();
          this.toastr.success(
            '<span class="now-ui-icons ui-1_bell-53"></span> Ha sido eliminado!',
            "Éxito!",
            {
              closeButton: true,
              enableHtml: true,
              toastClass: "alert alert-success alert-with-icon",
              positionClass: "toast-top-right",
            }
          );
        },
        (error) => {
          alert("Error: " + error.message);
        }
      );
    }
  }

  consultar() {
    this.service.getServiciosTecnitos("1").subscribe(
      (datos) => {
        this.servicios = datos;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
