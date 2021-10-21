import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { InventarioRepuesto } from "../model/Inventario";

@Injectable({
  providedIn: "root",
})
export class InventarioService {
  url = `${HttpClientHelper.baseURL}/repuestos`;

  constructor(private http: HttpClient) {}

  listar(estante: string, ubicacion: string, caja: string) {
    let p = {
      estante: estante,
      ubicacion: ubicacion,
      caja: caja,
    };
    return this.http.get<InventarioRepuesto[]>(this.url, { params: p });
  }

  obtener(id: string) {
    return this.http.get<InventarioRepuesto>(this.url + `/${id}`);
  }

  crearRepuesto(
    repuesto: InventarioRepuesto,
    idcaja: string,
    idestante: string,
    idubicacion: string,
    idusuario: string
  ) {
    let p = {
      idestante: idestante,
      idubicacion: idubicacion,
      idcaja: idcaja,
      idusuario: idusuario,
    };
    return this.http.post<InventarioRepuesto>(this.url, repuesto, {
      params: p,
    });
  }

  actualizarRepuesto(
    repuesto: InventarioRepuesto,
    idcaja: string,
    idestante: string,
    idubicacion: string
  ) {
    let p = {
      idestante: idestante,
      idubicacion: idubicacion,
      idcaja: idcaja,
    };
    return this.http.put<InventarioRepuesto>(
      this.url + `/${repuesto.idInventarioRepuesto}`,
      repuesto,
      {
        params: p,
      }
    );
  }

  cambiarEstado(id: number, status: number) {

    return this.http.put<InventarioRepuesto>(this.url + `/${id}/cambiarestado`, status);
  }


  ajustar(id: number, existencia: number) {

    return this.http.put<InventarioRepuesto>(this.url + `/${id}/ajustarexistencia`, existencia);
  }

  agregar(id: number, cantidad: number) {

    return this.http.put<InventarioRepuesto>(this.url + `/${id}/agregarexistencia`, cantidad);
  }
}
