import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
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
  selector: "app-editarrepuesto",
  templateUrl: "./editarrepuesto.component.html",
  styleUrls: ["./editarrepuesto.component.css"],
})
export class EditarrepuestoComponent implements OnInit {
  repuesto: InventarioRepuesto = new InventarioRepuesto();
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
    private service: InventarioService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.service.obtener(id).subscribe(
      (data) => {
        this.repuesto = data;
        this.idCajaSel = this.repuesto.caja.idCaja;
        this.idEstanteSel = this.repuesto.estante.idEstante;
        this.idUbicacionSel = this.repuesto.ubicacion.idUbicacion;
      },
      (err) => {
        console.log(err.message);

        this.rutas.navigate(["/notfound"]);
      }
    );
    this.initSelect();
  }

  initSelect() {
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
      .actualizarRepuesto(
        this.repuesto,
        this.idCajaSel.toString(),
        this.idEstanteSel.toString(),
        this.idUbicacionSel.toString()
      )
      .subscribe(
        (data) => {
          this.rutas.navigate(["inventario-repuestos"]);
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
}
