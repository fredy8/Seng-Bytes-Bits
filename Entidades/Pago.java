
class Pago {
  private Date fechaPago;
  private double cantidad;
  private Suscripcion suscripcion;

  public Pago(Date fechaPago, double cantidad, Suscripcion suscripcion){
    this.fechaPago = fechaPago;
    this.cantidad = cantidad;
    this.suscripcion = suscripcion;
  }
}