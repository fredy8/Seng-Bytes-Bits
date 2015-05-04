package Entidades;

import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Editor {

    private int id = -1;
    private String username, password, nombre, apellido;

    public Editor(String username, String password, String nombre, String apellido) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public static List<Editor> getAll() {
        List<Editor> editores = new ArrayList<>();

        try {
            ResultSet rs = Database.query("SELECT id, username, password, nombre, apellido FROM Editor");
            while(rs.next()) {
                editores.add(new Editor(rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("apellido")));
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

}
