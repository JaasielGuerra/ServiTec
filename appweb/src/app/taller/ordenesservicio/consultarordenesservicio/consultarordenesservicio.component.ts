import { Component, OnInit } from "@angular/core";
import moment from "moment";
import { ToastrService } from "ngx-toastr";
import { Tecnico } from "../../../areatecnica/model/Tecnico";
import { TecnicosService } from "../../../areatecnica/service/tecnicos.service";
import { Cliente } from "../../model/Cliente";
import { MotivoOrden } from "../../model/MotivoOrden";
import { Orden } from "../../model/Orden";
import { Prioridad } from "../../model/Prioridad";
import { ClientesService } from "../../services/clientes.service";
import { MotivosordenService } from "../../services/motivosorden.service";
import { OrdenesService } from "../../services/ordenes.service";
import { PrioridadesService } from "../../services/prioridades.service";

@Component({
  selector: "app-consultarordenesservicio",
  templateUrl: "./consultarordenesservicio.component.html",
  styleUrls: ["./consultarordenesservicio.component.css"],
})
export class ConsultarordenesservicioComponent implements OnInit {
  ordenes: Orden[] = new Array;

  tenicos: Tecnico[];
  prioridaddes: Prioridad[];
  motivos: MotivoOrden[];
  clientes: Cliente[];
  idTecnicoSel: string = "";
  idPriodidadSel: string = "";
  idMotivoSel: string = "";
  idClienteSel: string = "";

  fechaSel: string = moment().format("YYYY-MM-DD");

  constructor(
    private service: OrdenesService,
    private tecServ: TecnicosService,
    private prioSer: PrioridadesService,
    private motSer: MotivosordenService,
    private clienSer: ClientesService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {

    this.tecServ.listar("1").subscribe((d) => {
      this.tenicos = d;
    });
    this.prioSer.consultar().subscribe((d) => {
      this.prioridaddes = d;
    });
    this.motSer.consultar().subscribe((d) => {
      this.motivos = d;
    });
    this.clienSer.consultar("1", "").subscribe((d) => {
      this.clientes = d;
    });

    this.consultar();
  }

  eliminar(id: string) {
    if (confirm("¿Está seguro de eliminar la orden?")) {
      this.service.eliminar(id).subscribe(
        (d) => {
          this.consultar();
          this.msjSuccess();
        },
        (error) => {
          this.msjError(error);
        }
      );
    }
  }

  consultar() {
    this.service
      .consultar(
        "1",
        this.idTecnicoSel,
        this.idPriodidadSel,
        this.idMotivoSel,
        this.idClienteSel,
        this.fechaSel
      )
      .subscribe(
        (d) => {
          this.ordenes = d;
        },
        (e) => {
          console.log("error consultando: " + e.message);
        }
      );
  }

  msjSuccess() {
    this.toastr.success(
      '<span class="now-ui-icons ui-1_bell-53"></span> Orden eliminada',
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
