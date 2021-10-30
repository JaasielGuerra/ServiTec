package com.example.appservitec.models;

public class Producto {
    private String id_cliente;
    private String codigo_cliente;
    private String nombre_cliente;
    private String telefono;
    private String referencia;
    private String correo;
    private String estado;
    private String fecha_commit;
    private String hora_commit;
    private String id_usuario;

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_commit() {
        return fecha_commit;
    }

    public void setFecha_commit(String fecha_commit) {
        this.fecha_commit = fecha_commit;
    }

    public String getHora_commit() {
        return hora_commit;
    }

    public void setHora_commit(String hora_commit) {
        this.hora_commit = hora_commit;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
}
