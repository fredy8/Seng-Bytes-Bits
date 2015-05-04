package Entidades;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

class Autor extends Usuario {

    private String permiso, tipo;
    private boolean publicacionEsteAnio;

    public Autor(String username, String nombre, String apellido, String direccion, String tarjetaDeCredito, Timestamp fechaNacimiento, Timestamp fechaMiembro, Date expiracionTarjeta, int claveTarjeta, List<Suscripcion> suscripciones, String permiso, String tipo, boolean publicacionEsteAnio) {
        super(username, nombre, apellido, direccion, tarjetaDeCredito, fechaNacimiento, fechaMiembro, suscripciones);
        this.permiso = permiso;
        this.tipo = tipo;
        this.publicacionEsteAnio = publicacionEsteAnio;
    }

}
