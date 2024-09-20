package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Recorrido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String salida;
    private String destino;
    private String tipoViaje;
    private String duracion;
    private String tipoRecorrido;
    private String ramal;
    private String visibilidad;
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Parada> paradas = new ArrayList<>();
    @Transient
    private String paradaInput;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    // Getters y Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(String tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getTipoRecorrido() {
        return tipoRecorrido;
    }

    public void setTipoRecorrido(String tipoRecorrido) {
        this.tipoRecorrido = tipoRecorrido;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

     public String getParadaInput() {
        return paradaInput;
    }

    public void setParadaInput(String paradasInput) {
        this.paradaInput = paradasInput;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    @Override
    public String toString() {
        return "Recorrido{" +
                "titulo='" + titulo + '\'' +
                ", salida='" + salida + '\'' +
                ", destino='" + destino + '\'' +
                ", tipoViaje='" + tipoViaje + '\'' +
                ", duracion='" + duracion + '\'' +
                ", tipoRecorrido='" + tipoRecorrido + '\'' +
                ", ramal='" + ramal + '\'' +
                ", visibilidad='" + visibilidad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", paradas=" + paradas +
                '}';
    }
}