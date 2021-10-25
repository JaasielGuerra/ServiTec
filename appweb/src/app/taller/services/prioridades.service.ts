import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { Prioridad } from "../model/Prioridad";

@Injectable({
  providedIn: "root",
})
export class PrioridadesService {
  url = `${HttpClientHelper.baseURL}/prioridades`;

  constructor(private http: HttpClient) {}

  consultar() {
    let p = {
      status: "1",
    };
    return this.http.get<Prioridad[]>(this.url, { params: p });
  }

  obtener(id: string) {
    return this.http.get<Prioridad>(this.url + `/${id}`);
  }
}
