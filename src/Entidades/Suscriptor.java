/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Suscriptor {
    
    private int id = -1;
    private String nombre, apellido, direccion, tarjetaDeCredito;
    private int tiempoRestante;
    
    public Suscriptor(String nombre, String apellido, String direccion, String tarjeta_de_credito) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.tarjetaDeCredito = tarjeta_de_credito;
    }
    
    public static List<Suscriptor> getAll() {
        List<Suscriptor> suscriptores = new ArrayList<>();

        try {
            ResultSet rs = Database.query("SELECT id, nombre, apellido, direccion, tarjeta_de_credito, tiempo_restante FROM Suscriptor");
            while(rs.next()) {
                Suscriptor suscriptor = new Suscriptor(rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("tarjeta_de_credito"));
                suscriptor.id = rs.getInt("id");
                suscriptor.tiempoRestante = rs.getInt("tiempo_restante");
                suscriptores.add(suscriptor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Suscriptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suscriptores;
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
    
    public String getVencimiento() {
        String answer = "No Existente";
        if (this.tiempoRestante > 86399) { // 86399 less than a day
            Calendar date = Calendar.getInstance();
            date.add(Calendar.SECOND, this.tiempoRestante);
            SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
            answer = "Vence: " + dateParser.format(date.getTime());
        }
        return answer;
    }
    
    public String getTarjeta(){
        return tarjetaDeCredito;
    }
    
    public static Suscriptor getById(int id){
        Suscriptor suscriptor = null;
      
        try {
            ResultSet rs = Database.query("SELECT * FROM Suscriptor WHERE id = %d", id);
            if (rs.next()){
                suscriptor = new Suscriptor(rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("tarjeta_de_credito"));
                suscriptor.id = rs.getInt("id");
            }            
        } catch (SQLException ex) {
            Logger.getLogger(Suscriptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return suscriptor;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTiempoRestante() {
        return this.tiempoRestante;
    }

    public void setTiempoRestante(int time) {
        this.tiempoRestante = time;
    }
    
}
