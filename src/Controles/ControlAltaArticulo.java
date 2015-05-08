/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Database.Database;
import Entidades.Articulo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfredo_altamirano
 */
public class ControlAltaArticulo {
    
    public static void altaArticulo(Articulo articulo) {
        try {
            Database.update("INSERT INTO Articulo (titulo, texto) VALUES ('%s', '%s')", articulo.getTitulo(), articulo.getTexto());
            ResultSet rs = Database.query("SELECT id FROM Articulo ORDER BY id DESC LIMIT 1");
            articulo.setId(!rs.next() ? -1 : rs.getInt(1));
            articulo.getIdEditores().forEach((Integer idEditor) -> {
                try {
                    Database.update("INSERT INTO Editores_Articulos (id_articulo, id_editor) VALUES (%d, %d)", articulo.getId(), idEditor);
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
