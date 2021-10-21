import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { InventarioService } from "../../service/inventario.service";

@Component({
  selector: "app-agregarinventario",
  templateUrl: "./agregarinventario.component.html",
  styleUrls: ["./agregarinventario.component.css"],
})
export class AgregarinventarioComponent implements OnInit {
  id: number;
  cantidd: number;
  actual: number;
  descripcion: string;

  constructor(
    private rutas: Router,
    private toastr: ToastrService,
    private service: InventarioService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params.id;
    this.service.obtener(this.id.toString()).subscribe(
      (data) => {
        this.actual = data.existencia;
        this.descripcion = data.descripcion;
      },
      (err) => {
        console.log(err.message);

        this.rutas.navigate(["/notfound"]);
      }
    );
  }

  agregar() {
    this.service.agregar(this.id, this.cantidd).subscribe(
      (data) => {
        this.rutas.navigate(["inventario-repuestos"]);
        this.toastr.success(
          '<span class="now-ui-icons ui-1_bell-53"></span> Se han agregado ' +
            this.cantidd +
            " unidades!",
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
    );
  }
}
