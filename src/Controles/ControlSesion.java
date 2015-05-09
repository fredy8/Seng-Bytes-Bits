/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Entidades.Editor;

/**
 *
 * @author Beto
 */
public class ControlSesion {
    public static Editor abrirSesion(String username, String password){
        return Editor.authenticate(username, password);
    }
}
