/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Entidades.Articulo;
import java.util.List;

/**
 *
 * @author alfredo_altamirano
 */
public class ControlObtenerArticulos {
    
    public static List<Articulo> getArticulos() {
        return Articulo.getAll();
    }
    
    public static Articulo getArticuloById(int id) {
        return Articulo.getById(id);
    }
    
}
