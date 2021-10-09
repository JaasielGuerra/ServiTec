import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Categoria } from "../model/Categoria";

@Injectable({
  providedIn: "root",
})
export class CategoriaservicioService {
  urlCategorias =
    "http://192.168.1.20:8080/servitec/serviciostecnicos/categorias";

  constructor(private http: HttpClient) {}

  getCategorias(estado: string) {
    let p = {
      status: estado,
    };
    return this.http.get<Categoria[]>(this.urlCategorias, { params: p });
  }

  createCategoria(categoria: Categoria) {
    return this.http.post<Categoria>(this.urlCategorias, categoria);
  }

  actualizarCategoria(categoria: Categoria) {
    return this.http.put<Categoria>(
      this.urlCategorias + `/${categoria.idCategoriaServicio}`,
      categoria
    );
  }

  getCategoria(id: string) {
    return this.http.get<Categoria>(this.urlCategorias + `/${id}`);
  }

  eliminarCategoria(id: string) {
    return this.http.delete<Categoria>(this.urlCategorias + `/${id}`);
  }
}
