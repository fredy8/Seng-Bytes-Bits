package Entidades;


import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Articulo {

    private int id = -1;
    private String titulo, texto;
    private List<Integer> idEditores;
    private int votos;

    public Articulo(String titulo, String texto, List<Integer> editores) {
        this.titulo = titulo;
        this.texto = texto;
        this.idEditores = new ArrayList<>(editores);
    }

    public static List<Articulo> getAll() {
        Map<Integer, Articulo> articulos = new HashMap<>();
        try {
            ResultSet rs = Database.query("SELECT COUNT(id_juez) AS votos, Articulo.id, titulo, texto, Editor.id FROM Articulo JOIN Editores_Articulos on id_articulo = Articulo.id JOIN Editor ON Editor.id = id_editor JOIN Votos ON Articulo.id = Votos.id_articulo GROUP BY Votos.id_articulo");
            while(rs.next()) {
                int id = rs.getInt("Articulo.id");
                if (!articulos.containsKey(id)) {
                    Articulo articulo = new Articulo(rs.getString("titulo"), rs.getString("texto"), new ArrayList<>());
                    articulo.id = id;
                    articulo.votos = rs.getInt("votos");
                    articulos.put(id, articulo);
                }
                
                int idEditor = rs.getInt("Editor.id");
                articulos.get(id).idEditores.add(idEditor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>(articulos.values());
    }
    
    public void guardar() {
        try {
            Database.update("INSERT INTO Articulo (titulo, texto) VALUES ('%s', '%s')", this.titulo, this.texto);
            ResultSet rs = Database.query("SELECT id FROM Articulo ORDER BY id DESC LIMIT 1");
            this.id = !rs.next() ? -1 : rs.getInt(1);
            this.idEditores.forEach((Integer idEditor) -> {
                try {
                    Database.update("INSERT INTO Editores_Articulos (id_articulo, id_editor) VALUES (%d, %d)", this.id, idEditor);
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getTitulo(){
        return titulo;
    }

    public String getResumen(){
        return texto.substring(0, Math.min(texto.length(), 140)) + "...";
    }
    
    public static Articulo getById(int id) {
        Articulo articulo = null;
        try {
            ResultSet rs = Database.query("SELECT Articulo.id, titulo, texto, Editor.id from Articulo JOIN Editores_Articulos on id_articulo = Articulo.id JOIN Editor ON Editor.id = id_editor WHERE Articulo.id = %s", id);
            while(rs.next()) {
                if (articulo == null) {
                    articulo = new Articulo(rs.getString("titulo"), rs.getString("texto"), new ArrayList<>());
                    articulo.id = rs.getInt("Articulo.id");
                }
                
                articulo.idEditores.add(rs.getInt("Editor.id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return articulo;
    }
    
    public List<Editor> getEditores() {
        List<Editor> editores = new ArrayList<>();
        String ids = String.join(",", this.idEditores.stream().map((Integer id) -> Integer.toString(id)).collect(Collectors.toList()));
        try {
            ResultSet rs = Database.query("SELECT username, password, nombre, apellido, tipo, fecha_de_miembro FROM Editor WHERE id in (%s)", ids);
            while(rs.next()) {
                editores.add(new Editor(rs.getString("username"), rs.getString("password"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("tipo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return editores;
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }

    boolean isPublished() {
        try {
            ResultSet rs = Database.query("SELECT id FROM Articulo WHERE id_revista IS NOT NULL AND id = %d", this.id);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public int getVotos() {
        return votos;
    }
    
}
