
class Juez extends Usuario {
  private Date tiempoEnCargo;

  public Juez(String username, String nombre, String apellido, String direccion, String tarjetaDeCredito, Date fechaNacimiento, Date fechaMiembro, Date expiracionTarjeta, int claveTarjeta, List<Suscripcion> suscripciones, Date tiempoEnCargo) {
    super;
    this.tiempoEnCargo = tiempoEnCargo;
  }

  static Juez fromResultSet(ResultSet rs) {
    return new Juez(...);
  }

  public static crearJuez(Juez juez) {
    Database.update(“”);
  }

  public static Juez getJuezByUsername() {
    ResultSet rs = Database.query(“”);
    return !rs.next() ? null : Juez.fromResultSet(rs);
  }
}
