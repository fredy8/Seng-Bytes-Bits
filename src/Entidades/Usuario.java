package Entidades;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int id = -1;
    private String username, nombre, apellido, direccion, tarjetaDeCredito;
    private Timestamp fechaNacimiento, fechaMiembro;
    private List<Suscripcion> suscripciones;

    public Usuario(String username, String nombre, String apellido, String direccion, String tarjetaDeCredito, Timestamp fechaNacimiento, Timestamp fechaMiembro, List<Suscripcion> suscripciones) {
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.tarjetaDeCredito = tarjetaDeCredito;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaMiembro = fechaMiembro;
        this.suscripciones = new ArrayList<>(suscripciones);
    }

}
