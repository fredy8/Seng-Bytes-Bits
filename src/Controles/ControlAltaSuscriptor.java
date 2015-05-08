/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Database.Database;
import Entidades.Suscriptor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfredo_altamirano
 */
public class ControlAltaSuscriptor {
    
    public static void altaSuscriptor(Suscriptor suscriptor) {
        try {
            Database.update("INSERT INTO Suscriptor (nombre, apellido, direccion, tarjeta_de_credito) VALUES ('%s', '%s', '%s', '%s')", suscriptor.getNombre(), suscriptor.getApellido(), suscriptor.getDireccion(), suscriptor.getTarjeta());
            ResultSet rs = Database.query("SELECT id FROM Suscriptor ORDER BY id DESC LIMIT 1");
            suscriptor.setId(!rs.next() ? -1 : rs.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Suscriptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
