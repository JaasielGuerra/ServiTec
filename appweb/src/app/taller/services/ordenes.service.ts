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

  actualizar(
    file: File,
    orden: Orden,
    idTecnico: string,
    idMotivo: string,
    idPrioridad: string,
    idCliente: string
  ) {
    const formData = new FormData();
    formData.append("imageFile", file);
    formData.append("orden", JSON.stringify(orden));
    formData.append("idtecnico", idTecnico);
    formData.append("idmotivo", idMotivo);
    formData.append("idprioridad", idPrioridad);
    formData.append("idcliente", idCliente);

    return this.http.put<Orden>(this.url + `/${orden.idOrden}`, formData);
  }

  eliminar(id: string) {
    return this.http.delete(this.url + `/${id}`);
  }

  consultar(
    status: string,
    tec: string,
    prio: string,
    mot: string,
    client: string,
    fechaIngreso: string
  ) {
    let p = {
      status: status,
      tecnico: tec,
      prioridad: prio,
      motivo: mot,
      cliente: client,
      fecha: fechaIngreso,
    };

    return this.http.get<Orden[]>(this.url, { params: p });
  }

  consultarAtender(
    status: string,
    tec: string,
    prio: string,
    mot: string,
    client: string,
    fechaIngreso: string
  ) {
    let p = {
      status: status,
      tecnico: tec,
      prioridad: prio,
      motivo: mot,
      cliente: client,
      fecha: fechaIngreso,
    };

    return this.http.get<Orden[]>(this.url + `/atender`, { params: p });
  }

  consultarCobrar(idorden: string, client: string) {
    let p = {
      orden: idorden,
      cliente: client,
    };

    return this.http.get<Orden[]>(this.url + `/cobrar`, { params: p });
  }

  consultarCobradas(idorden: string, client: string, fecha: string) {
    let p = {
      orden: idorden,
      cliente: client,
      fecha: fecha,
    };

    return this.http.get<Orden[]>(this.url + `/cobradas`, { params: p });
  }

  obtener(id: string) {
    return this.http.get<Orden>(this.url + `/${id}`);
  }

  atender(id: string) {
    return this.http.put<Orden>(this.url + `/atender/${id}`, undefined);
  }

  ponerPendiente(orden: Orden) {
    return this.http.put<Orden>(
      this.url + `/ponerpendiente/${orden.idOrden}`,
      orden
    );
  }

  finalizarOrden(orden: Orden) {
    return this.http.put<Orden>(
      this.url + `/finalizar/${orden.idOrden}`,
      orden
    );
  }

  cobrarOrden(orden: Orden) {
    return this.http.put<Orden>(this.url + `/cobrar/${orden.idOrden}`, orden);
  }

  entregarOrden(orden: Orden) {
    return this.http.put<Orden>(this.url + `/entregar/${orden.idOrden}`, orden);
  }
}
