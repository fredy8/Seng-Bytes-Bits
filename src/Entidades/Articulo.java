package Entidades;


import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

class Articulo {

    private int id = -1;
    private String tipoPublicacion;
    private Date fechaPublicacion;
    private Autor autor;
    private Juez juez;

    public Articulo(String tipoPub, Date f, Autor a, Juez j) {
        tipoPublicacion = tipoPub;
        fechaPublicacion = f;
        autor = a;
        juez = j;
    }

    public int getId() {
        return id;
    }
}
