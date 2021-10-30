package com.example.appservitec.interfaces;

import com.example.appservitec.models.Producto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductoApi {
    @GET("api/obtenerporcodigo/{codigo}")
    public Call<Producto> find(@Path("id") String id);
}
