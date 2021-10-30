import { Tecnico } from "../../areatecnica/model/Tecnico";
import { Cliente } from "./Cliente";
import { EstadoOrden } from "./EstadoOrden";
import { MotivoOrden } from "./MotivoOrden";
import { Prioridad } from "./Prioridad";
import { RepuestoEmpleado } from "./RepuestoEmpleado";
import { ServicioAplicado } from "./ServicioAplicado";

export class Orden {
  idOrden: number;
  fechaMaximaEntrega: string;
  fechaIngreso: string;
  fechaEntrega: string;
  descripcionOrden: string;
  descripcionRecibido: string;
  imagenReferencia: any;
  diagnosticoTecnico: string;
  aplicable: number;
  descripcionExtra: string;
  costoExtra: number;
  totalCostoServicio: number;
  tecnico: Tecnico;
  prioridad: Prioridad;
  estadoOrden: EstadoOrden;
  motivoOrden: MotivoOrden;
  cliente: Cliente;
  costoRepuestos: number;
  costoServicios: number;

  repuestoEmpleados: RepuestoEmpleado[];
  servicioAplicados: ServicioAplicado[];
}
