import java.util.Date;

public class Retiro extends Transaccion {

    private boolean limiteSuperado;
    private String motivo;

    public Retiro(String idTransaccion, double monto, Date fecha, Empleado empleadoGestor, Cuenta cuentaRegistra, String motivo) {
        super(idTransaccion, monto, fecha, empleadoGestor, cuentaRegistra);
        this.motivo = motivo;
        this.limiteSuperado = false;
    }

    // Resta saldo si no supera el límite y hay fondos //
    @Override
    public boolean procesar(Cuenta c) {
        double limite = 1000;

        if (monto > limite) {
            limiteSuperado = true;
            return false;
        }

        boolean ok = c.retirar(monto);

        if (ok) {
            c.addMovimiento(this);
        }

        return ok;
    }

    @Override
    public String toString() {
        return "Retiro | " + super.toString() +
                " | Motivo: " + motivo +
                " | Límite superado: " + limiteSuperado;
    }
}
