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

    // Devuelve el monto de la transacción
    public double getMonto() {
        return monto;
    }

    // Método abstracto que se implementa según el tipo de transacción 
    public abstract boolean procesar(Cuenta c);

    // Retorna información para mostrar o registrar 
    @Override
    public String toString() {
        return "ID: " + idTransaccion +
                " | Monto: " + monto +
                " | Fecha: " + fecha +
                " | Gestor: " + (empleadoGestor != null ? empleadoGestor.getNombreCompleto() : "N/A") +
                " | Cuenta: " + (cuentaRegistra != null ? cuentaRegistra.getNumeroCuenta() : "N/A");
    }
}
