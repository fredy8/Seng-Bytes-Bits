
class Revista {

  private int id; 
  private String nombre, tipo;
  private Boolean autorizado;
  private Date fechaPublicacion;
  private List<Articulo> articulos;

  public Revista(String nombre, String tipo, Boolean autorizado, Date fechaPublicacion, List<Articulo> articulos){
    this.nombre = nombre;
    this.tipo = tipo;
    this.autorizado = autorizado;
    this.fechaPublicacion = fechaPublicacion;
    this.articulos = new ArrayList<Articulo>(articulos);
  }

  static Autor fromResultSet(ResultSet rs) {
    Revista revista = Revista(...);
    revista.id = rs.getInt(“id”);
  }

  public static crearRevista(Revista revista) {
    Database.update(“”);
  }

  public static Revista getRevistaById(int id) {
    ResultSet rs = Database.query(“”);
    return !rs.next() ? null : Revista.fromResultSet(rs);
  }
}
