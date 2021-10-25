import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { Orden } from "../model/Orden";

@Injectable({
  providedIn: "root",
})
export class OrdenesService {
  url = `${HttpClientHelper.baseURL}/ordenesservicio`;

  constructor(private http: HttpClient) {}

  crearOrden(
    file: File,
    orden: Orden,
    idTecnico: string,
    idMotivo: string,
    idPrioridad: string,
    idUsuario: string,
    idCliente: string
  ) {
    const formData = new FormData();
    formData.append("imageFile", file);
    formData.append("orden", JSON.stringify(orden));
    formData.append("idtecnico", idTecnico);
    formData.append("idmotivo", idMotivo);
    formData.append("idprioridad", idPrioridad);
    formData.append("idusuario", idUsuario);
    formData.append("idcliente", idCliente);

    return this.http.post<Orden>(this.url, formData);
  }
}
