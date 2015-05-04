package Entidades;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Revista {

    private int id;
    private String nombre, tipo;
    private Boolean autorizado;
    private Date fechaPublicacion;
    private List<Articulo> articulos;

    public Revista(String nombre, String tipo, Boolean autorizado, Date fechaPublicacion, List<Articulo> articulos) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.autorizado = autorizado;
        this.fechaPublicacion = fechaPublicacion;
        this.articulos = new ArrayList<>(articulos);
    }

    static Autor fromResultSet(ResultSet rs) {
        return null;
    }

    public static void crearRevista(Revista revista) {

    }

    public static Revista getRevistaById(int id) {
        return null;
    }
}