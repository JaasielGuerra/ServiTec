import { Component, OnInit } from "@angular/core";
import moment from "moment";
import { textChangeRangeIsUnchanged } from "typescript";
import { Cliente } from "../../model/Cliente";
import { Orden } from "../../model/Orden";
import { ClientesService } from "../../services/clientes.service";
import { OrdenesService } from "../../services/ordenes.service";

@Component({
  selector: "app-consultarservicioscobrados",
  templateUrl: "./consultarservicioscobrados.component.html",
  styleUrls: ["./consultarservicioscobrados.component.css"],
})
export class ConsultarservicioscobradosComponent implements OnInit {
  ordenesCobradas: Orden[];
  totalCobros: number;
  cantidadOrdenes: number;

  clientes: Cliente[];

  idOrdenBuscar: string = "";
  idClienteSel: string = "";
  fechaSel: string = moment().format("YYYY-MM-DD");

  constructor(
    private service: OrdenesService,
    private clienSer: ClientesService
  ) {}

  ngOnInit(): void {
    this.clienSer.consultar("1", "").subscribe((d) => {
      this.clientes = d;
      this.consultar();
    });
  }

  consultar() {
    if (this.idOrdenBuscar == undefined) {
      this.idOrdenBuscar = "";
    }
    this.service
      .consultarCobradas(this.idOrdenBuscar, this.idClienteSel, this.fechaSel)
      .subscribe(
        (d) => {
          this.ordenesCobradas = d;
          this.calcularTotales();
        },
        (error) => {
          alert("Error al consultar " + error.message);
        }
      );
  }

  calcularTotales() {
    this.totalCobros = this.ordenesCobradas
      .map((o) => o.totalCostoServicio)
      .reduce((a, b) => a + b, 0);
    this.cantidadOrdenes = this.ordenesCobradas.length;
  }
}
