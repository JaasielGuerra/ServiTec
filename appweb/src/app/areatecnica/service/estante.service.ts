import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { Estante } from "../model/Estante";

@Injectable({
  providedIn: "root",
})
export class EstanteService {
  url = `${HttpClientHelper.baseURL}/repuestos/estantes`;

  constructor(private http: HttpClient) {}

  listar(estado: string) {
    let p = {
      status: estado,
    };
    return this.http.get<Estante[]>(this.url, { params: p });
  }

  crear(entity: Estante) {
    return this.http.post<Estante>(this.url, entity);
  }

  actualizar(entity: Estante) {
    return this.http.put<Estante>(this.url + `/${entity.idEstante}`, entity);
  }

  obtener(id: string) {
    return this.http.get<Estante>(this.url + `/${id}`);
  }

  eliminar(id: string) {
    return this.http.delete<Estante>(this.url + `/${id}`);
  }
}
