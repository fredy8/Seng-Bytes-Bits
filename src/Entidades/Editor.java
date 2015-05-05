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

    private int id = -1, tipo;
    private String username, password, nombre, apellido;
    private Timestamp fecha_de_miembro;

    public Editor(String username, String password, String nombre, String apellido, Timestamp fecha_de_miembro, int tipo) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_de_miembro = fecha_de_miembro;
        this.tipo = tipo;
    }
    
    public static List<Editor> getAll() {
        List<Editor> editores = new ArrayList<>();

        try {
            ResultSet rs = Database.query("SELECT id, username, password, nombre, apellido, tipo, fecha_de_miembro FROM Editor");
            while(rs.next()) {
                Editor editor = new Editor(rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("apellido"), rs.getTimestamp("fecha_de_miembro"), rs.getInt("tipo"));
                editor.id = rs.getInt("id");
                editores.add(editor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editores;
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
            ResultSet rs = Database.query("SELECT id, username, password, nombre, apellido FROM Editor WHERE username = '%s' and password = '%s'", username, password);
            if (!rs.next()) {
                return null;
            }
            
            editor = new Editor(rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("apellido"));
            editor.id = rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return editor;
    }

    public boolean canVote(Articulo articulo) {
        return true;
    }

}
