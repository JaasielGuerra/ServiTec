import { ServicioTecnico } from "../../areatecnica/model/ServicioTecnico";
import { Orden } from "./Orden";

export class ServicioAplicado{
  idServicioAplicado: number;
  precio: number;
  cantidad: number;
  orden: Orden = new Orden();
  servicio: ServicioTecnico = new ServicioTecnico();
}
