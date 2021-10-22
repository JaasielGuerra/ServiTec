import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { Usuario } from "../model/Usuario";

@Injectable({
  providedIn: "root",
})
export class UsuariosService {
  url = `${HttpClientHelper.baseURL}/usuarios`;

  constructor(private httt: HttpClient) {}

  listar(e: string) {
    let p = {
      status: e,
    };
    return this.httt.get<Usuario[]>(this.url, { params: p });
  }
}
