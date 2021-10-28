import { Component, OnInit } from "@angular/core";
import { ToastrService } from "ngx-toastr";
import { Caja } from "../../model/Caja";
import { Estante } from "../../model/Estante";
import { InventarioRepuesto } from "../../model/Inventario";
import { Ubicacion } from "../../model/Ubicacion";
import { CajaService } from "../../service/caja.service";
import { EstanteService } from "../../service/estante.service";
import { InventarioService } from "../../service/inventario.service";
import { UbicacionService } from "../../service/ubicacion.service";

@Component({
  selector: "app-consultainventario",
  templateUrl: "./consultainventario.component.html",
  styleUrls: ["./consultainventario.component.css"],
})
export class ConsultainventarioComponent implements OnInit {
  inventario: InventarioRepuesto[];
  estantes: Estante[];
  ubicaciones: Ubicacion[];
  cajas: Caja[];

  idEstanteSel: string;
  idUbicacionSel: string;
  idCajaSel: string;

  constructor(
    private service: InventarioService,
    private cajaService: CajaService,
    private estanteService: EstanteService,
    private ubicaionService: UbicacionService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.cargarFiltros();
    this.consultarInventario();
  }

  consultarInventario() {
    this.service
      .listar(this.idEstanteSel, this.idUbicacionSel, this.idCajaSel)
      .subscribe(
        (data) => {
          this.inventario = data;
        },
        (error) => {
          alert("Error al consultar: " + error.message);
        }
      );
  }

  desactivar(id: number) {
    if (confirm("¿Está seguro de desactivarlo?")) {
      this.service.cambiarEstado(id, 0).subscribe(
        (data) => {
          this.toastr.success(
            '<span class="now-ui-icons ui-1_bell-53"></span> Ha sido desactivado!',
            "Éxito!",
            {
              closeButton: true,
              enableHtml: true,
              toastClass: "alert alert-success alert-with-icon",
              positionClass: "toast-top-right",
            }
          );
          this.consultarInventario();
        },
        (error) => {
          alert("Error al cambiar estado: " + error.message);
        }
      );
    }
  }

  activar(id: number) {
    this.service.cambiarEstado(id, 1).subscribe(
      (data) => {
        this.toastr.success(
          '<span class="now-ui-icons ui-1_bell-53"></span> Ha sido activado!',
          "Éxito!",
          {
            closeButton: true,
            enableHtml: true,
            toastClass: "alert alert-success alert-with-icon",
            positionClass: "toast-top-right",
          }
        );
        this.consultarInventario();
      },
      (error) => {
        alert("Error al cambiar estado: " + error.message);
      }
    );
  }

  cargarFiltros() {
    this.idEstanteSel = "0";
    this.idUbicacionSel = "0";
    this.idCajaSel = "0";

    this.estanteService.listar("1").subscribe(
      (data) => {
        this.estantes = data;
      },
      (error) => {
        alert("Error estantesService: " + error.message);
      }
    );
    this.ubicaionService.listar("1").subscribe(
      (data) => {
        this.ubicaciones = data;
      },
      (error) => {
        alert("Error ubicacionService: " + error.message);
      }
    );
    this.cajaService.listar("1").subscribe(
      (data) => {
        this.cajas = data;
      },
      (error) => {
        alert("Error cajaService: " + error.message);
      }
    );
  }
}
