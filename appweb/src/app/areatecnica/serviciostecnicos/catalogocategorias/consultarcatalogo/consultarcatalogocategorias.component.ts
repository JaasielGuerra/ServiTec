import { Component, OnInit } from "@angular/core";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { ToastrService } from "ngx-toastr";
import { Categoria } from "../../../model/Categoria";
import { CategoriaservicioService } from "../../../service/categoriaservicio.service";

@Component({
  selector: "app-consultarcatalogocategorias",
  templateUrl: "./consultarcatalogocategorias.component.html",
  styleUrls: ["./consultarcatalogocategorias.component.css"],
})
export class ConsultarcatalogocategoriasComponent implements OnInit {
  categoriasList: Categoria[];
  categoriaModel = new Categoria();

  constructor(
    private modalService: NgbModal,
    private service: CategoriaservicioService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.consultar();
  }

  //-------acciones --------------
  nuevaCategoria(modal) {
    this.categoriaModel = new Categoria();
    this.modalService.open(modal);
  }

  guardarCategoria() {
    if (this.categoriaModel.idCategoriaServicio == undefined) {
      this.service.createCategoria(this.categoriaModel).subscribe(
        (d) => {
          this.modalService.dismissAll();
          this.consultar();
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
          alert("Error: " + err.message);
        }
      );
    }
    if (this.categoriaModel.idCategoriaServicio) {
      this.service.actualizarCategoria(this.categoriaModel).subscribe(
        (d) => {
          this.modalService.dismissAll();

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
          alert("Ocurrio un error: " + error.message);
        }
      );
    }
  }

  consultar() {
    this.service.getCategorias("1").subscribe(
      (d) => {
        this.categoriasList = d;
      },
      (error) => {
        alert("Errot: " + error.message);
      }
    );
  }

  editar(id: number, modal) {
    this.service.getCategoria(id.toString()).subscribe(
      (d) => {
        this.categoriaModel = d;
      },
      (err) => {
        alert("Error: " + err.message);
        return;
      }
    );

    this.modalService.open(modal);
  }

  eliminar(id: number) {
    if (confirm("¿Está seguro de eliminarlo?")) {
      this.service.eliminarCategoria(id.toString()).subscribe(
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
        (err) => {
          alert("Error: " + err.message);
          return;
        }
      );
    }
  }
}
