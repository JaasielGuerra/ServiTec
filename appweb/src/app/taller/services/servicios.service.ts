import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { ServicioAplicado } from "../model/ServicioAplicado";

@Injectable({
  providedIn: "root",
})
export class ServiciosService {
  url = `${HttpClientHelper.baseURL}/ordenesservicio`;

  constructor(private http: HttpClient) {}

  addServicioOrden(idOrden: string, s: ServicioAplicado) {
    return this.http.post<ServicioAplicado>(
      this.url + `/${idOrden}/addservicio`,
      s
    );
  }

  listarServiciosAplicados(idOrden: string) {
    return this.http.get<ServicioAplicado[]>(
      this.url + `/${idOrden}/serviciosaplicados`
    );
  }

  eliminar(id: string) {
    return this.http.delete(this.url + `/eliminarservicioaplicado/${id}`)
  }
}
