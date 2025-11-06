import java.util.*;

public abstract class Transaccion {

    // Atributos de la transacción
    protected double monto;
    protected Date fecha;
    protected String hora;
    protected String tipo;
    protected String idTransaccion;
    protected Cuenta cuentaAsociada;

    // Constructor
    public Transaccion(double monto, Date fecha, String hora, String tipo, String idTransaccion, Cuenta cuentaAsociada) {
        this.monto = monto;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.idTransaccion = idTransaccion;
        this.cuentaAsociada = cuentaAsociada;
    }

    // Getter del monto
    public double getMonto() {
        return monto;
    }

    // Devuelve fecha (si luego deseas mezclar hora, se puede formatear)
    public Date getFechaHora() {
        return fecha;
    }

    // Método polimórfico. Cada transacción implementa su propia lógica.
    public abstract boolean procesar(Cuenta c);

    // toString para detallar la transacción
    @Override
    public String toString() {
        String numCuenta = (cuentaAsociada != null) ? cuentaAsociada.getNumeroCuenta() : "Sin cuenta";
        return "Transacción: " + tipo +
               " | ID: " + idTransaccion +
               " | Monto: " + monto +
               " | Fecha: " + fecha +
               " | Hora: " + hora +
               " | Cuenta Asociada: " + numCuenta;
    }
}
