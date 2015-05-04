package Entidades;

import java.sql.Timestamp;
import java.util.List;

public class Juez extends Usuario {

    public Juez(String username, String nombre, String apellido, String direccion, String tarjetaDeCredito, Timestamp fechaNacimiento, Timestamp fechaMiembro, List<Suscripcion> suscripciones) {
        super(username, nombre, apellido, direccion, tarjetaDeCredito, fechaNacimiento, fechaMiembro, suscripciones);
    }
    
}
