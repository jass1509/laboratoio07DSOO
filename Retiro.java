import java.util.Date;

public class Retiro extends Transaccion {

    private String motivo;

    // Constructor
    public Retiro(double monto, Date fecha, String hora, String idTransaccion, Cuenta cuentaAsociada, String motivo) {
        super(monto, fecha, hora, "Retiro", idTransaccion, cuentaAsociada);
        this.motivo = motivo;
    }

    // Procesa el retiro (resta saldo si hay suficiente)
    @Override
    public boolean procesar(Cuenta c) {
        if (c == null) {
            return false;
        }

        // Validación de saldo suficiente
        if (c.getSaldo() >= monto) {
            c.setSaldo(c.getSaldo() - monto);
            return true; // retiro exitoso
        } else {
            return false; // saldo insuficiente
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Motivo: " + motivo;
    }
}
