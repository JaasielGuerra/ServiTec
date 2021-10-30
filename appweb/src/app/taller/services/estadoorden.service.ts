import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { EstadoOrden } from "../model/EstadoOrden";

@Injectable({
  providedIn: "root",
})
export class EstadoordenService {
  url = `${HttpClientHelper.baseURL}/estadosordenes`;

  constructor(private http: HttpClient) {}

  consultar() {
    let p = {
      status: "1",
    };
    return this.http.get<EstadoOrden[]>(this.url, { params: p });
  }

  listarEstadosAtender() {
    return this.http.get<EstadoOrden[]>(this.url + `/atender`);
  }

  obtener(id: string) {
    return this.http.get<EstadoOrden>(this.url + `/${id}`);
  }
}
