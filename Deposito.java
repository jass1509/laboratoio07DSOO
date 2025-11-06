import java.util.Date;

public class Deposito extends Transaccion {

    private String motivo;

    // Constructor
    public Deposito(double monto, Date fecha, String hora, String idTransaccion, Cuenta cuentaAsociada, String motivo) {
        super(monto, fecha, hora, "Depósito", idTransaccion, cuentaAsociada);
        this.motivo = motivo;
    }

    // Procesa el depósito (suma saldo a la cuenta)
    @Override
    public boolean procesar(Cuenta c) {
        if (c == null) {
            return false;
        }

        // Suma el monto al saldo de la cuenta
        c.setSaldo(c.getSaldo() + monto);
        return true;
    }

    // Detalle adicional mostrando motivo (opcional)
    @Override
    public String toString() {
        return super.toString() + " | Motivo: " + motivo;
    }
}
