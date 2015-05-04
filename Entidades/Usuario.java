
public class Usuario {
  private String username, nombre, apellido, direccion, tarjetaDeCredito;
  private Date fechaNacimiento, fechaMiembro, expiracionTarjeta;
  private int claveTarjeta;
  private List<Suscripcion> suscripciones;

  public Usuario(String username, String nombre, String apellido, String direccion, String tarjetaDeCredito, Date fechaNacimiento, Date fechaMiembro, Date expiracionTarjeta, int claveTarjeta, List<Suscripcion> suscripciones) {
    this.username = username;
    this.nombre = nombre;
    this.apellido = apellido;
    this.direccion = direccion;
    this.tarjetaDeCredito = tarjetaDeCredito;
    this.fechaNacimiento = fechaNacimiento;
    this.fechaMiembro = fechaMiembro;
    this.expiracionTarjeta = expiracionTarjeta;
    this.claveTarjeta = claveTarjeta;
    this.suscripciones = new ArrayList<Suscripcion>(suscripciones);
  }

  static Usuario fromResultSet(ResultSet rs) {
    return new Usuario(...);
  }

  public static crearUsuario(Usuario usuario) {
    Database.update(“”);
  }

  public static Usuario getUsuarioByUsername() {
    ResultSet rs = Database.query(“”);
    return !rs.next() ? null : Usuario.fromResultSet(rs);
  }
}
