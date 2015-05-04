
class Articulo {

  private int id = -1;
  private String tipoPublicacion;
  private Date fechaPublicacion;
  private Autor autor;
  private Juez juez;

  public Articulo(String tipoPub, Date f, Autor a, Juez j) {
    tipoPublicacion = tipoPub;
    fechaPublicacion = f;
    autor = a;
    juez = j;
  }

  static Articulo fromResultSet(ResultSet rs) {
    Articulo articulo = new Articulo(rs.getString(“Articulos.tipo”), Date.parse(rs.getInt(“Articulos.fechaPublicacion”)), Autor.fromResultSet(rs), Juez.fromResultSet(rs));
    articulo.id = rs.getInt(“id”);
  }

  public static getArticuloById(int idArticulo) {
    return !rs.next() ? null : Articulo.fromResultSet(Database.query(“SELECT * FROM Articulos JOIN Autores ON Autores.id = idAutor JOIN Jueces ON Jueces.id = idJuez WHERE idArticulo = ” + idArticulo));
  }

  public int getId() {
    return id;
  }
}
