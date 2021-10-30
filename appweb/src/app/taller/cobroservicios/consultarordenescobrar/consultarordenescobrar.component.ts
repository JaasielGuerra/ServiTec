import { Component, OnInit } from "@angular/core";
import { Cliente } from "../../model/Cliente";
import { Orden } from "../../model/Orden";
import { ClientesService } from "../../services/clientes.service";
import { OrdenesService } from "../../services/ordenes.service";

@Component({
  selector: "app-consultarordenescobrar",
  templateUrl: "./consultarordenescobrar.component.html",
  styleUrls: ["./consultarordenescobrar.component.css"],
})
export class ConsultarordenescobrarComponent implements OnInit {
  ordenesCobrar: Orden[];

  clientes: Cliente[];

  idOrdenBuscar: string = "";
  idClienteSel: string = "";

  totalAplicadas: number;
  totalNoAplicadas: number;

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
      .consultarCobrar(this.idOrdenBuscar, this.idClienteSel)
      .subscribe(
        (d) => {
          this.ordenesCobrar = d;
          this.contarOrdenes();
        },
        (error) => {
          alert("Error al consultar " + error.message);
        }
      );
  }

  contarOrdenes() {
    this.totalAplicadas = this.ordenesCobrar.filter(
      (o) => o.aplicable === 1
    ).length;
    this.totalNoAplicadas = this.ordenesCobrar.filter(
      (o) => o.aplicable === 0
    ).length;
  }
}
