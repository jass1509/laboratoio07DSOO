import java.util.Date;

public class Retiro extends Transaccion {

    private boolean limiteSuperado;

    public Retiro(String idTransaccion, double monto, Date fecha, Empleado empleadoGestor, Cuenta cuentaRegistra) {
        super(idTransaccion, monto, fecha, empleadoGestor, cuentaRegistra);
        this.limiteSuperado = false;
    }

    // Procesa el retiro (solo si saldo suficiente)
    @Override
    public boolean procesar(Cuenta c) {
        if (c == null) return false;

        if (c.getSaldo() >= monto) {
            c.setSaldo(c.getSaldo() - monto);
            return true;
        } else {
            limiteSuperado = true;
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Exceso de retiro: " + (limiteSuperado ? "Sí" : "No");
    }
}
