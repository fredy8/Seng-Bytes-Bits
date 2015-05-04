package Entidades;

import java.sql.ResultSet;

class Suscripcion {

    private int id;
    private String tipo;
    private double precio, descuento;

    public Suscripcion(String tipo, double precio, double descuento) {
        this.tipo = tipo;
        this.precio = precio;
        this.descuento = descuento;
    }

    static Suscripcion fromResultSet(ResultSet rs) {
        return null;
    }

    public static void crearSuscripcion(Suscripcion suscripcion) {

    }

    public static Suscripcion getSuscripcionById(int id) {
        return null;
    }
}
