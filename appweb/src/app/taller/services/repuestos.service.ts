import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { RepuestoEmpleado } from "../model/RepuestoEmpleado";

@Injectable({
  providedIn: "root",
})
export class RepuestosService {
  url = `${HttpClientHelper.baseURL}/ordenesservicio`;

  constructor(private http: HttpClient) {}

  addRepuestoOrden(idOrden: string, r: RepuestoEmpleado) {
    return this.http.post<RepuestoEmpleado>(
      this.url + `/${idOrden}/addrepuesto`,
      r
    );
  }

  listarRepuestosEmpleados(idOrden: string) {
    return this.http.get<RepuestoEmpleado[]>(
      this.url + `/${idOrden}/repuestosempleados`
    );
  }

  eliminar(id: string) {
    return this.http.delete(this.url + `/eliminarrepuestoempleado/${id}`)
  }
}
