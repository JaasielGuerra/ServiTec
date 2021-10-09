import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { ToastrService } from "ngx-toastr";
import { Categoria } from "../../model/Categoria";
import { ServicioTecnico } from "../../model/ServicioTecnico";
import { CategoriaservicioService } from "../../service/categoriaservicio.service";
import { ServiciostecnicosService } from "../../service/serviciostecnicos.service";

@Component({
  selector: "app-crearserviciotecnico",
  templateUrl: "./crearserviciotecnico.component.html",
  styleUrls: ["./crearserviciotecnico.component.css"],
})
export class CrearserviciotecnicoComponent implements OnInit {
  servicio = new ServicioTecnico();
  categorias: Categoria[];
  idCatSeleccionada: number = -1;
  categoriaModel = new Categoria();

  constructor(
    private modalService: NgbModal,
    private rutas: Router,
    private service: ServiciostecnicosService,
    private serviceCategorias: CategoriaservicioService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.consultarCategorias();
  }

  //--------------metodos de accion--------------------
  guardar() {
    this.service
      .createServicioTecnico(this.servicio, this.idCatSeleccionada.toString())
      .subscribe(
        (data) => {
          this.rutas.navigate(["servicios-tecnicos"]);
          this.toastr.success(
            '<span class="now-ui-icons ui-1_bell-53"></span> Ha sido registrado!',
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

  nuevaCategoria(modal) {
    this.categoriaModel = new Categoria();
    this.modalService.open(modal);
  }

  guardarCategoria() {
    this.serviceCategorias.createCategoria(this.categoriaModel).subscribe(
      (d) => {
        this.modalService.dismissAll();
        this.idCatSeleccionada = -1;
        this.consultarCategorias();
        this.toastr.success(
          '<span class="now-ui-icons ui-1_bell-53"></span> Ha sido registrada!',
          "Éxito!",
          {
            closeButton: true,
            enableHtml: true,
            toastClass: "alert alert-success alert-with-icon",
            positionClass: "toast-top-right",
          }
        );
      },
      (err) => {
        this.toastr.error(
          '<span class="now-ui-icons ui-1_bell-53"></span> Descripción: ' + err,
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

  consultarCategorias() {
    this.serviceCategorias.getCategorias("1").subscribe((data) => {
      this.categorias = data;
    });
  }
}
