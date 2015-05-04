
class Autor extends Usuario {
  private String permiso, tipo;
  private boolean publicacionEsteAnio;

  public Autor(String username, String nombre, String apellido, String direccion, String tarjetaDeCredito, Date fechaNacimiento, Date fechaMiembro, Date expiracionTarjeta, int claveTarjeta, List<Suscripcion> suscripciones, String permiso, String tipo, boolean publicacionEsteAnio) {
    super;
    this.permiso = permiso;
    this.tipo = tipo;
    this.publicacionesEsteAnio = publicacionesEsteAnio;
    
  }

  static Autor fromResultSet(ResultSet rs) {
    return new Autor(...);
  }

  public static crearAutor(Autor autor) {
    Database.update(“”);
  }

  public static Autor getAutorByUsername() {
    ResultSet rs = Database.query(“”);
    return !rs.next() ? null : Autor.fromResultSet(rs);
  }
}
