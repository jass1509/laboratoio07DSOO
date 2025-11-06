import java.util.Date;

public class Deposito extends Transaccion {

    private String motivo;

    public Deposito(String idTransaccion, double monto, Date fecha, Empleado empleadoGestor, Cuenta cuentaRegistra, String motivo) {
        super(idTransaccion, monto, fecha, empleadoGestor, cuentaRegistra);
        this.motivo = motivo;
    }

    // Deposita el dinero y registra el movimiento
    @Override
    public boolean procesar(Cuenta c) {
        c.depositar(monto);
        c.addMovimiento(this);
        return true;
    }

    @Override
    public String toString() {
        return "Depósito | " + super.toString() + " | Motivo: " + motivo;
    }
}
