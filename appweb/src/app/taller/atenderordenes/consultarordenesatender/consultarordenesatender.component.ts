import { Component, OnInit } from "@angular/core";
import moment from "moment";
import { Tecnico } from "../../../areatecnica/model/Tecnico";
import { TecnicosService } from "../../../areatecnica/service/tecnicos.service";
import { Cliente } from "../../model/Cliente";
import { EstadoOrden } from "../../model/EstadoOrden";
import { MotivoOrden } from "../../model/MotivoOrden";
import { Orden } from "../../model/Orden";
import { Prioridad } from "../../model/Prioridad";
import { ClientesService } from "../../services/clientes.service";
import { EstadoordenService } from "../../services/estadoorden.service";
import { MotivosordenService } from "../../services/motivosorden.service";
import { OrdenesService } from "../../services/ordenes.service";
import { PrioridadesService } from "../../services/prioridades.service";

@Component({
  selector: "app-consultarordenesatender",
  templateUrl: "./consultarordenesatender.component.html",
  styleUrls: ["./consultarordenesatender.component.css"],
})
export class ConsultarordenesatenderComponent implements OnInit {
  ordenesAtender: Orden[];

  estados: EstadoOrden[];
  prioridaddes: Prioridad[];
  motivos: MotivoOrden[];
  clientes: Cliente[];

  idEstadoSel: string = "0";
  idPriodidadSel: string = "";
  idMotivoSel: string = "";
  idClienteSel: string = "";

  fechaSel: string = moment().format("YYYY-MM-DD");

  tecnicoEnSesion: Tecnico = new Tecnico();

  totalReservadas: number;
  totalAtendiendo: number;
  totalPendientes: number;

  constructor(
    private service: OrdenesService,
    private prioSer: PrioridadesService,
    private motSer: MotivosordenService,
    private clienSer: ClientesService,
    private estaroSer: EstadoordenService,
    private tecServ: TecnicosService
  ) {}

  ngOnInit(): void {

    this.estaroSer.listarEstadosAtender().subscribe((d) => {
      this.estados = d;
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

    this.tecServ.obtener("3").subscribe((d) => {
      this.tecnicoEnSesion = d;
      this.consultar();
    });
  }

  consultar() {
    this.service
      .consultarAtender(
        this.idEstadoSel,
        this.tecnicoEnSesion.idTecnico + "",
        this.idPriodidadSel,
        this.idMotivoSel,
        this.idClienteSel,
        this.fechaSel
      )
      .subscribe(
        (d) => {
          this.ordenesAtender = d;
          this.contarOrdenes();
        },
        (e) => {
          console.log("error consultando: " + e.message);
        }
      );
  }

  contarOrdenes() {
    this.totalReservadas = this.ordenesAtender.filter(
      (o) => o.estadoOrden.idEstadoOrden === 1
    ).length;
    this.totalAtendiendo = this.ordenesAtender.filter(
      (o) => o.estadoOrden.idEstadoOrden === 2
    ).length;
    this.totalPendientes = this.ordenesAtender.filter(
      (o) => o.estadoOrden.idEstadoOrden === 3
    ).length;
  }
}
