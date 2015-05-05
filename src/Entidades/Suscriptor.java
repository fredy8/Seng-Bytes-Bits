/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Suscriptor {
    
    private int id = -1;
    private String nombre, apellido, direccion;
    
    public Suscriptor(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }
    
    public static List<Suscriptor> getAll() {
        List<Suscriptor> suscriptores = new ArrayList<>();

        try {
            ResultSet rs = Database.query("SELECT id, nombre, apellido, direccion FROM Suscriptor");
            while(rs.next()) {
                Suscriptor suscriptor = new Suscriptor(rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"));
                suscriptor.id = rs.getInt("id");
                suscriptores.add(suscriptor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suscriptores;
    }

    public void guardar() {
        try {
            Database.update("INSERT INTO Suscriptor (nombre, apellido, direccion) VALUES ('%s', '%s', '%s')", this.nombre, this.apellido, this.direccion);
            ResultSet rs = Database.query("SELECT id FROM Suscriptor ORDER BY id DESC LIMIT 1");
            this.id = !rs.next() ? -1 : rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Suscriptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getId() {
        return id;
    }
    
    public String getFullName() {
        return nombre + " " + apellido;
    }
    
    public String getDireccion() {
        return direccion;
    }
}
