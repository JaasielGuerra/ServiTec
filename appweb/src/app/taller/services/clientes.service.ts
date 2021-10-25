import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { Cliente } from "../model/Cliente";

@Injectable({
  providedIn: "root",
})
export class ClientesService {
  url = `${HttpClientHelper.baseURL}/clientes`;

  constructor(private http: HttpClient) {}

  consultar(status: string, buscar: string) {
    let p = {
      status: status,
      buscar: buscar,
    };
    return this.http.get<Cliente[]>(this.url, { params: p });
  }
}
