import { Caja } from "./Caja";
import { Estante } from "./Estante";
import { Ubicacion } from "./Ubicacion";

export class InventarioRepuesto{

  idInventarioRepuesto: number;
  descripcion: string;
  precio: number;
  existencia: number;
  estado: number;
  estante: Estante;
  ubicacion: Ubicacion;
  caja: Caja;


}
