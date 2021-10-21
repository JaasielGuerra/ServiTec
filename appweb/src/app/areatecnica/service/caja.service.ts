import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { Caja } from "../model/Caja";

@Injectable({
  providedIn: "root",
})
export class CajaService {

  url = `${HttpClientHelper.baseURL}/repuestos/cajas`;

  constructor(private http: HttpClient) {}

  listar(estado: string) {
    let p = {
      status: estado,
    };
    return this.http.get<Caja[]>(this.url, { params: p });
  }

  crear(entity: Caja) {
    return this.http.post<Caja>(this.url, entity);
  }

  actualizar(entity: Caja) {
    return this.http.put<Caja>(this.url + `/${entity.idCaja}`, entity);
  }

  obtener(id: string) {
    return this.http.get<Caja>(this.url + `/${id}`);
  }

  eliminar(id: string) {
    return this.http.delete<Caja>(this.url + `/${id}`);
  }
}
