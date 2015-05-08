/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Database.Database;
import Entidades.Editor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfredo_altamirano
 */
public class ControlAltaEditor {
    
    public static void altaEditor(Editor editor) {
        try {
            Database.update("INSERT INTO Editor (username, password, nombre, apellido, tipo) VALUES ('%s', '%s', '%s', '%s', %d)", editor.getUsername(), editor.getPassword(), editor.getNombre(), editor.getApellido(), editor.getTipo());
            ResultSet rs = Database.query("SELECT id FROM Editor ORDER BY id DESC LIMIT 1");
            editor.setId(!rs.next() ? -1 : rs.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
