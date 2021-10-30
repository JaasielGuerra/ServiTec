import { InventarioRepuesto } from "../../areatecnica/model/Inventario";
import { Orden } from "./Orden";

export class RepuestoEmpleado{
  idRepuestoEmpleado: number;
  precio: number;
  cantidad: number;
  orden: Orden = new Orden();
  inventarioRepuesto: InventarioRepuesto = new InventarioRepuesto();
}
