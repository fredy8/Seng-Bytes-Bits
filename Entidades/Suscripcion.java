
class Suscripcion {

  private int id;
  private String tipo;
  private double precio, descuento;

  public Suscripcion(String tipo, double precio, double descuento){
    this.tipo = tipo;
    this.precio = precio;
    this.descuento = descuento;
  }

  static Suscripcion fromResultSet(ResultSet rs) {
    Suscripcion suscripcion = new Suscripcion(...);
    suscripcion.id = rs.getInt(“Suscripciones.id”);
  }

  public static crearSuscripcion(Suscripcion suscripcion) {
    Database.update(“”);
  }

  public static Suscripcion getSuscripcionById(int id) {
    ResultSet rs = Database.query(“”);
    return !rs.next() ? null : Autor.fromResultSet(rs);
  }
}
