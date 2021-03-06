import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
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
  selector: "app-nuevorepuesto",
  templateUrl: "./nuevorepuesto.component.html",
  styleUrls: ["./nuevorepuesto.component.css"],
})
export class NuevorepuestoComponent implements OnInit {
  repuesto: InventarioRepuesto;
  estantes: Estante[];
  ubicaciones: Ubicacion[];
  cajas: Caja[];

  idEstanteSel: number;
  idUbicacionSel: number;
  idCajaSel: number;

  constructor(
    private cajaService: CajaService,
    private estanteService: EstanteService,
    private ubicaionService: UbicacionService,
    private rutas: Router,
    private toastr: ToastrService,
    private service: InventarioService
  ) {}

  ngOnInit(): void {
    this.repuesto = new InventarioRepuesto();
    this.repuesto.estado = 1;
    this.initSelect();
  }
  initSelect() {
    this.idEstanteSel = 1;
    this.idUbicacionSel = 1;
    this.idCajaSel = 1;

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

  guardar() {
    this.service
      .crearRepuesto(
        this.repuesto,
        this.idCajaSel.toString(),
        this.idEstanteSel.toString(),
        this.idUbicacionSel.toString(),
        "1"
      )
      .subscribe(
        (data) => {
          this.rutas.navigate(["inventario-repuestos"]);
          this.toastr.success(
            '<span class="now-ui-icons ui-1_bell-53"></span> Ha sido registrado!',
            "??xito!",
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
            '<span class="now-ui-icons ui-1_bell-53"></span> Descripci??n: ' +
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
