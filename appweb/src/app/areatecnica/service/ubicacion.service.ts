import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { Ubicacion } from "../model/Ubicacion";

@Injectable({
  providedIn: "root",
})
export class UbicacionService {
  url = `${HttpClientHelper.baseURL}/repuestos/ubicaciones`;

  constructor(private http: HttpClient) {}

  listar(estado: string) {
    let p = {
      status: estado,
    };
    return this.http.get<Ubicacion[]>(this.url, { params: p });
  }

  crear(entity: Ubicacion) {
    return this.http.post<Ubicacion>(this.url, entity);
  }

  actualizar(entity: Ubicacion) {
    return this.http.put<Ubicacion>(
      this.url + `/${entity.idUbicacion}`,
      entity
    );
  }

  obtener(id: string) {
    return this.http.get<Ubicacion>(this.url + `/${id}`);
  }

  eliminar(id: string) {
    return this.http.delete<Ubicacion>(this.url + `/${id}`);
  }
}
