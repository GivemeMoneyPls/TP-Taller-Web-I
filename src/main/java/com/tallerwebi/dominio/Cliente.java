package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String password;
    private String provincia;
    private String localidad;
    private String domicilio;
    private Integer codigoPostal;
    private Integer telefono;
    private Integer telefonoMovil;
    private Boolean abonado;
    private Boolean admin;

    public Long getCodigoCliente() { return codigoCliente; }

    public void setCodigoCliente(Long codigoCliente) { this.codigoCliente = codigoCliente; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDni() { return dni; }

    public void setDni(String dni) { this.dni = dni; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getProvincia() { return provincia; }

    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getLocalidad() { return localidad; }

    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getDomicilio() { return domicilio; }

    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public Integer getCodigoPostal() { return codigoPostal; }

    public void setCodigoPostal(Integer codigoPostal) { this.codigoPostal = codigoPostal; }

    public Integer getTelefono() { return telefono; }

    public void setTelefono(Integer telefono) { this.telefono = telefono; }

    public Integer getTelefonoMovil() { return telefonoMovil; }

    public void setTelefonoMovil(Integer telefonoMovil) { this.telefonoMovil = telefonoMovil; }

    public Boolean getAbonado() { return abonado; }

    public void setAbonado(Boolean abonado) { this.abonado = abonado; }

    public Boolean getAdmin() { return admin; }

    public void setAdmin(Boolean admin) { this.admin = admin; }
}
