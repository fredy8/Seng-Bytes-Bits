package Entidades;

import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Suscripcion {

    private int id;
    private double costo;
    private int duracion;

    public Suscripcion(int duracion, double costo) {
        this.costo = costo;
        this.duracion = duracion;
    }

    public void guardar() {
        try {
            Database.update("INSERT INTO Suscripcion (costo, duracion) VALUES ('%s', '%d')",  this.costo, this.duracion);
            ResultSet rs = Database.query("SELECT id FROM Suscripcion ORDER BY id DESC LIMIT 1");
            this.id = !rs.next() ? -1 : rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Suscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
