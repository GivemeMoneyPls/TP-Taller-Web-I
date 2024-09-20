package com.tallerwebi.dominio;

public class Vehiculo {
    private String patente;
    private Integer cantidadAsientosTotales;
    private Integer cantidadAsientosOcupados;


    public Vehiculo(String patente, Integer cantidadAsientosTotales) {
        this.patente = patente;
        this.cantidadAsientosTotales = cantidadAsientosTotales;
    }

    public String getPatente() {return "";}
    public Integer getCantidadAsientosTotales() {return cantidadAsientosTotales;}
    public Integer getCantidadAsientosOcupados(){return cantidadAsientosOcupados;}


}
