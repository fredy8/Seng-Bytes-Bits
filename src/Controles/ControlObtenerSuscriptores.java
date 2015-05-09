/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Entidades.Suscriptor;
import java.util.List;

/**
 *
 * @author alfredo_altamirano
 */
public class ControlObtenerSuscriptores {
    
    public static List<Suscriptor> obtenerSuscriptores() {
        return Suscriptor.getAll();
    }
    
}
