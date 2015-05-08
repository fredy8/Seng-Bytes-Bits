/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Database.Database;
import Entidades.Articulo;
import Entidades.Revista;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfredo_altamirano
 */
public class ControlAltaRevista {
    
    public static void altaRevista(Revista revista) {
        try {
            Database.update("INSERT INTO Revista (titulo) VALUES ('%s')", revista.getTitulo());
            ResultSet rs = Database.query("SELECT id FROM Revista ORDER BY id DESC LIMIT 1");
            revista.setId(!rs.next() ? -1 : rs.getInt(1));
            revista.getIdArticulos().forEach((Integer idArticulo) -> {
                try {
                    Database.update("UPDATE Articulo SET id_revista = %d where id = %d", revista.getId(), idArticulo);
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            Database.update("DELETE FROM Votos");
        } catch (SQLException ex) {
            Logger.getLogger(Revista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
