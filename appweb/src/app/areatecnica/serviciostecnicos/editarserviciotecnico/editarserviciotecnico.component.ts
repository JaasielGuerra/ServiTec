import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { ServiciostecnicosService } from "../../service/serviciostecnicos.service";
import { ServicioTecnico } from "../../model/ServicioTecnico";
import { Categoria } from "../../model/Categoria";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { CategoriaservicioService } from "../../service/categoriaservicio.service";

@Component({
  selector: "app-editarserviciotecnico",
  templateUrl: "./editarserviciotecnico.component.html",
  styleUrls: ["./editarserviciotecnico.component.css"],
})
export class EditarserviciotecnicoComponent implements OnInit {
  servicioTecnicoEditar: ServicioTecnico = new ServicioTecnico();
  categorias: Categoria[];
  idCatSeleccionada: number = -1;
  categoriaModel = new Categoria();

  constructor(
    private service: ServiciostecnicosService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private rutas: Router,
    private modalService: NgbModal,
    private serviceCategorias: CategoriaservicioService
  ) {}

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params.id;

    this.service.getServicioTecnito(id).subscribe(
      (data) => {
        this.servicioTecnicoEditar = data;
        this.idCatSeleccionada = this.servicioTecnicoEditar.categoriaServicio.idCategoriaServicio;
        this.consultarCategorias();
      },
      (err) => {
        console.log(err.message);

        this.rutas.navigate(["/notfound"]);
      }
    );
  }

  actualizar() {
    this.service
      .actualizarServicioTecnico(
        this.servicioTecnicoEditar,
        this.idCatSeleccionada.toString()
      )
      .subscribe(
        (data) => {
          this.rutas.navigate(["servicios-tecnicos"]);
          this.toastr.success(
            '<span class="now-ui-icons ui-1_bell-53"></span> Ha sido actualizado!',
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
