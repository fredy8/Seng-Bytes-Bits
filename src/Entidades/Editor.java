package Entidades;

import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Editor {

    int id = -1, tipo;
    private String username, password, nombre, apellido;
    private Timestamp fecha_de_miembro;
    private final int MAX_VOTES = 9;

    public Editor(String username, String password, String nombre, String apellido, int tipo) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo = tipo;
    }
    
    public static List<Editor> getAllAuthors() {
        List<Editor> editores = new ArrayList<>();

        try {
            ResultSet rs = Database.query("SELECT id, username, password, nombre, apellido, tipo, fecha_de_miembro FROM Editor WHERE tipo = 0");
            while(rs.next()) {
                Editor editor = new Editor(rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("tipo"));
                editor.id = rs.getInt("id");
                editores.add(editor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editores;
    }
    
    public void guardar() {
        try {
            Database.update("INSERT INTO Editor (username, password, nombre, apellido, tipo) VALUES ('%s', '%s', '%s', '%s', %d)", this.username, this.password, this.nombre, this.apellido, this.tipo);
            ResultSet rs = Database.query("SELECT id FROM Editor ORDER BY id DESC LIMIT 1");
            this.id = !rs.next() ? -1 : rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getId() {
        return id;
    }
    
    public String getFullName() {
        return nombre + " " + apellido; 
    }
    
    public static Editor authenticate(String username, String password) {
        Editor editor = null;
        try {
            ResultSet rs = Database.query("SELECT id, username, password, nombre, apellido, tipo, fecha_de_miembro FROM Editor WHERE username = '%s' and password = '%s'", username, password);
            if (!rs.next()) {
                return null;
            }
            
            editor = new Editor(rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("tipo"));
            editor.id = rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return editor;
    }

    public boolean canVote(Articulo articulo) {
        int articuloId = articulo.getId();
        if (this.tipo != 1) {
            return false;
        }
        
        if (articulo.authorsPublishedRecently()) {
            return false;
        }
        
        try {
            ResultSet rs = Database.query("SELECT COUNT(*) FROM Votos WHERE id_juez = %d", this.id);
            if (rs.next() && rs.getInt(1) >= MAX_VOTES) {
                return false;
            }
            
            if (voted(articuloId) || articulo.isPublished()) {
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public void vote(Articulo articulo) {
        if (!canVote(articulo)) {
            return;
        }
        
        try {
            Database.update("INSERT INTO Votos (id_juez, id_articulo) VALUES (%d, %d)", this.id, articulo.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean voted(int articuloId) {
        try {
            ResultSet rs = Database.query("SELECT id_juez FROM Votos WHERE id_juez = %d AND id_articulo = %d", this.id, articuloId);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public boolean isAuthor() {
        return this.tipo == 0;
    }

    public boolean isJudge() {
        return this.tipo == 1;
    }

    public boolean isChief() {
        return this.tipo == 2;
    }

    public boolean recentlyPublished() {
        try {
            ResultSet rs = Database.query("SELECT Editor.id AS editorId, Revista.id AS revistaId FROM Revista JOIN Articulo ON Articulo.id_revista = Revista.id JOIN Editores_Articulos ON Editores_Articulos.id_articulo = Articulo.id JOIN Editor ON Editor.id = Editores_Articulos.id_editor WHERE Editor.id = %d GROUP BY Editor.id ORDER BY revistaId DESC LIMIT 12;", this.id);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

}
