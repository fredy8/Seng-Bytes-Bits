/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Entidades.Revista;
import java.util.List;

/**
 *
 * @author alfredo_altamirano
 */
public class ControlObtenerRevistas {
    
    public static List<Revista> obtenerRevistas() {
        return Revista.getAll();
    }
    
}
