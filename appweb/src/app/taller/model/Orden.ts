import { Tecnico } from "../../areatecnica/model/Tecnico";
import { Cliente } from "./Cliente";
import { MotivoOrden } from "./MotivoOrden";
import { Prioridad } from "./Prioridad";

export class Orden {
  idOrden: number;
  fechaMaximaEntrega: string;
  fechaIngreso: string;
  fechaEntrega: string;
  descripcionOrden: string;
  descripcionRecibido: string;
  diagnosticoTecnico: string;
  aplicable: number;
  descripcionExtra: string;
  costoExtra: number;
  totalCostoServicio: number;
  tecnico: Tecnico;
  prioridad: Prioridad;
  estadoOrden: Orden;
  motivoOrden: MotivoOrden;
  cliente: Cliente;
}
