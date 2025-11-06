import java.util.Date;

public class Deposito extends Transaccion {

    private String motivo;

    public Deposito(String idTransaccion, double monto, Date fecha, Empleado empleadoGestor, Cuenta cuentaRegistra, String motivo) {
        super(idTransaccion, monto, fecha, empleadoGestor, cuentaRegistra);
        this.motivo = motivo;
    }

    // Procesa el depósito (suma al saldo)
    @Override
    public boolean procesar(Cuenta c) {
        if (c == null) return false;
        c.setSaldo(c.getSaldo() + monto);
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " | Motivo: " + motivo;
    }
}

