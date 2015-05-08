/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Database.Database;
import Entidades.Suscriptor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfredo_altamirano
 */
public class ControlExtenderSuscripcion {
 
     public static void setTiempoRestante(Suscriptor suscriptor, int years) {
         try {
             int time = suscriptor.getTiempoRestante() + (years * 31536000);
             suscriptor.setTiempoRestante(time);
             Database.update("UPDATE Suscriptor SET tiempo_restante = tiempo_restante + %d WHERE id = %d", time, suscriptor.getId());
         } catch (SQLException ex) {
             Logger.getLogger(ControlExtenderSuscripcion.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
