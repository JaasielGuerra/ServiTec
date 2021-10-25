import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { MotivoOrden } from "../model/MotivoOrden";

@Injectable({
  providedIn: "root",
})
export class MotivosordenService {
  url = `${HttpClientHelper.baseURL}/motivosorden`;

  constructor(private http: HttpClient) {}

  consultar() {
    let p = {
      status: "1",
    };
    return this.http.get<MotivoOrden[]>(this.url, { params: p });
  }
}
