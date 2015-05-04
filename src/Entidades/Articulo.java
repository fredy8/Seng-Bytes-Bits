package Entidades;


import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Articulo {

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

    public static List<Articulo> getAll() {
        ResultSet rs = Database.query("SELECT id, tipo, fecha_de_publicacion, publicado, autorizado FROM Articulo");
        List<Articulo> articulos = new ArrayList<>();
        try {
            while(rs.next()) {
                articulos.add(new Articulo(rs.getString("tipo"), rs.getTimestamp("fecha_de_publicacion"), null, null));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return articulos;
    }
    
    public void guardar() {
        Database.update("INSERT INTO Articulo () VALUES ()");
    }

    public String getTipoPublicacion(){
        return tipoPublicacion;
    }

}
