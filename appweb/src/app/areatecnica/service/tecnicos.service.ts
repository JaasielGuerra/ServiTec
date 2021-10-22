import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { Tecnico } from "../model/Tecnico";

@Injectable({
  providedIn: "root",
})
export class TecnicosService {
  url = `${HttpClientHelper.baseURL}/tecnicos`;

  constructor(private http: HttpClient) {}

  crear(t: Tecnico, idusuario: string) {
    let p = {
      idusuario: idusuario,
    };
    return this.http.post<Tecnico>(this.url, t, { params: p });
  }

  listar(estado: string) {
    let p = {
      status: estado,
    };
    return this.http.get<Tecnico[]>(this.url, { params: p });
  }

  listarTodos() {
    return this.http.get<Tecnico[]>(this.url, {});
  }

  obtener(id: string) {
    return this.http.get<Tecnico>(this.url + `/${id}`);
  }

  actualizar(t: Tecnico, idusuario: string) {
    let p = {
      idusuario: idusuario,
    };
    return this.http.put<Tecnico>(this.url + `/${t.idTecnico}`, t, {
      params: p,
    });
  }

  eliminar(id: string) {
    return this.http.delete(this.url + `/${id}`);
  }

  disponibilidad(id: string) {
    return this.http.get<number>(this.url + `/${id}/disponibilidad`);
  }

  cambiarEstado(id: string, estado: number) {
    return this.http.put<Tecnico>(this.url + `/${id}/cambiarestado`, estado);
  }
}
