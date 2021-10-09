import { Component, OnInit } from "@angular/core";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { ToastrService } from "ngx-toastr";
import { CategoriaservicioService } from "../../../service/categoriaservicio.service";
import { Categoria } from "../../../model/Categoria";

@Component({
  selector: "app-crearcategoria",
  templateUrl: "./crearcategoria.component.html",
  styleUrls: ["./crearcategoria.component.css"],
})
export class CrearcategoriaComponent implements OnInit {
  categoriaModel = new Categoria();

  constructor(
    private modalService: NgbModal,
    private toastr: ToastrService,
    private service: CategoriaservicioService
  ) {}

  ngOnInit(): void {}

  //-----------acciones-----------
  nuevaCategoria(modal) {
    this.categoriaModel = new Categoria();
    this.modalService.open(modal);
  }

  guardarCategoria() {
    this.service.createCategoria(this.categoriaModel).subscribe(
      (d) => {
        this.modalService.dismissAll();

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
        alert("Error: " + err);
        return false;
      }
    );
    return true;
  }
}
