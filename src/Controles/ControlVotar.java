/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Database.Database;
import Entidades.Articulo;
import Entidades.Editor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfredo_altamirano
 */
public class ControlVotar {
    public static void vote(Editor editor, Articulo articulo) {
        if (!editor.canVote(articulo)) {
            return;
        }
        
        try {
            Database.update("INSERT INTO Votos (id_juez, id_articulo) VALUES (%d, %d)", articulo.getId(), articulo.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
