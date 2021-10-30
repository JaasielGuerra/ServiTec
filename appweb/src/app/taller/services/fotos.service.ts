import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { HttpClientHelper } from "../../baseURL";
import { FotosDiagnostico } from "../model/FotosDiagnostico";
import { RepuestoEmpleado } from "../model/RepuestoEmpleado";

@Injectable({
  providedIn: "root",
})
export class FotosService {
  url = `${HttpClientHelper.baseURL}/ordenesservicio`;

  constructor(private http: HttpClient) {}

  addFoto(idOrden: string, fd: FotosDiagnostico, file: File) {
    const formData = new FormData();
    formData.append("imageFile", file);
    formData.append("fotodiagnostico", JSON.stringify(fd));

    return this.http.post<FotosDiagnostico>(
      this.url + `/${idOrden}/addfoto`,
      formData
    );
  }

  listarFotosDiagnostico(idOrden: string) {
    return this.http.get<FotosDiagnostico[]>(
      this.url + `/${idOrden}/fotosdiagnostico`
    );
  }

  eliminar(id: string) {
    return this.http.delete(this.url + `/eliminarfotodiagnostico/${id}`);
  }
}
