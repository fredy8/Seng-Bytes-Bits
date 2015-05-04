package Entidades;


import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Articulo {

    private int id = -1;
    private String titulo, texto;
    private List<Integer> idEditores;

    public Articulo(String titulo, String texto, List<Integer> editores) {
        this.titulo = titulo;
        this.texto = texto;
        this.idEditores = new ArrayList<>(editores);
    }

    public static List<Articulo> getAll() {
        ResultSet rs = Database.query("SELECT id, titulo, texto, Editor.id from Articulo JOIN Editores_Articulos on id_articulo = Articulo.id JOIN Editor ON Editor.id = id_editor");
        Map<Integer, Articulo> articulos = new HashMap<>();
        try {
            while(rs.next()) {
                int id = rs.getInt("Articulo.id");
                if (!articulos.containsKey(id)) {
                    articulos.put(id, new Articulo(rs.getString("titulo"), rs.getString("texto"), new ArrayList<>()));
                }
                
                int idEditor = rs.getInt("Editor.id");
                articulos.get(id).idEditores.add(idEditor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>(articulos.values());
    }
    
    public void guardar() {
        Database.update("INSERT INTO Articulo () VALUES ()");
    }

    public String getTitulo(){
        return titulo;
    }

    public String getResumen(){
        return texto;
    }
}
