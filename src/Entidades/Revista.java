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
    
    public void guardar() {
        try {
            Database.update("INSERT INTO Revista (titulo) VALUES ('%s')", this.titulo);
            ResultSet rs = Database.query("SELECT id FROM Revista ORDER BY id DESC LIMIT 1");
            this.id = !rs.next() ? -1 : rs.getInt(1);
            this.idArticulos.forEach((Integer idArticulo) -> {
                try {
                    Database.update("UPDATE Articulos SET id_revista = %d where id = %d", this.id, idArticulo);
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            Database.update("DELETE FROM Votos");
        } catch (SQLException ex) {
            Logger.getLogger(Revista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Revista> getAll() throws SQLException {
        Map<Integer, Revista> revistas = new HashMap<>();
        
        ResultSet rs = Database.query("SELECT Revista.id, Articulo.id, titulo FROM Revista JOIN Articulo ON Revista.id = Articulo.id ");
        while(rs.next()) {
            int id = rs.getInt("Revista.id");
            if (!revistas.containsKey(id)) {
                Revista revista = new Revista(rs.getString("titulo"), new ArrayList<>());
                revista.id = id;
            }
            int idArticulo = rs.getInt("Articulo.id");
            revistas.get(id).idArticulos.add(idArticulo);
        }
        
        return new ArrayList<>(revistas.values());
    }
}
