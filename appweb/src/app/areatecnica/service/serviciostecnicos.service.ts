import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ServicioTecnico } from "../model/ServicioTecnico";
import { HttpClientHelper } from "../../baseURL";

@Injectable({
  providedIn: "root",
})
export class ServiciostecnicosService {
  constructor(private http: HttpClient) {}

  urlServicios = `${HttpClientHelper.baseURL}/serviciostecnicos`;

  getServiciosTecnitos(estado: string) {
    let p = new HttpParams({ fromObject: { status: estado } });
    return this.http.get<ServicioTecnico[]>(this.urlServicios, { params: p });
  }

  getServicioTecnito(id: string) {
    return this.http.get<ServicioTecnico>(this.urlServicios + `/${id}`);
  }

  createServicioTecnico(servicio: ServicioTecnico, idCat: string) {
    let p = {
      idcategoria: idCat,
      idusuario: "1",
    };
    return this.http.post<ServicioTecnico>(this.urlServicios, servicio, {
      params: p,
    });
  }

  actualizarServicioTecnico(servicio: ServicioTecnico, idCat: string) {
    let p = {
      idcategoria: idCat,
      idusuario: "1",
    };
    return this.http.put<ServicioTecnico>(this.urlServicios+`/${servicio.idServicio}`, servicio, {
      params: p,
    });
  }
  eliminarServicioTenico(id: string) {
    return this.http.delete<ServicioTecnico>(this.urlServicios + `/${id}`);
  }
}
