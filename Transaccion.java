import java.util.Date;

public abstract class Transaccion {

    protected String idTransaccion;
    protected double monto;
    protected Date fecha;
    protected Empleado empleadoGestor;
    protected Cuenta cuentaRegistra;

    public Transaccion(String idTransaccion, double monto, Date fecha, Empleado empleadoGestor, Cuenta cuentaRegistra) {
        this.idTransaccion = idTransaccion;
        this.monto = monto;
        this.fecha = fecha;
        this.empleadoGestor = empleadoGestor;
        this.cuentaRegistra = cuentaRegistra;
    }

    // Retorna el monto
    public double getMonto() {
        return monto;
    }

    // Método polimórfico que se redefine en subclases
    public abstract boolean procesar(Cuenta c);

    // Representación en texto de la transacción
    @Override
    public String toString() {
        return "ID: " + idTransaccion +
               " | Monto: " + monto +
               " | Fecha: " + fecha +
               " | Gestor: " + (empleadoGestor != null ? empleadoGestor.getNombre() : "No asignado") +
               " | Cuenta: " + (cuentaRegistra != null ? cuentaRegistra.getNumeroCuenta() : "Sin cuenta");
    }
}
