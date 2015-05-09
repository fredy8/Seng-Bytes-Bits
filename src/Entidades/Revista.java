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

public class Revista {

    private int id;
    private String titulo;
    private List<Integer> idArticulos;

    public Revista(String titulo, List<Integer> articulos) {
        this.titulo = titulo;
        this.idArticulos = new ArrayList<>(articulos);
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public List<Integer> getIdArticulos() {
        return new ArrayList<>(idArticulos);
    }
    
    public static List<Revista> getAll() {
        try {
            Map<Integer, Revista> revistas = new HashMap<>();
            
            ResultSet rs = Database.query("SELECT Revista.id, Articulo.id, Revista.titulo FROM Revista, Articulo WHERE Revista.id = Articulo.id_revista");
            while(rs.next()) {
                int id = rs.getInt("Revista.id");
                if (!revistas.containsKey(id)) {
                    Revista revista = new Revista(rs.getString("titulo"), new ArrayList<>());
                    revista.id = id;
                    revistas.put(id, revista);
                }
                int idArticulo = rs.getInt("Articulo.id");
                revistas.get(id).idArticulos.add(idArticulo);
            }
            
            return new ArrayList<>(revistas.values());
        } catch (SQLException ex) {
            Logger.getLogger(Revista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getTituto() {
        return titulo;
    }

    public void setId(int id) {
        this.id = id;
    }
}
