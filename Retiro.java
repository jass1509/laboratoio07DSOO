import java.util.Date;

public class Retiro extends Transaccion {

    private boolean limiteSuperado;
    private String motivo;
    private double limite = 1000; // Límite simple permitido

    public Retiro(String idTransaccion, double monto, Date fecha, Empleado empleadoGestor, Cuenta cuentaRegistra, String motivo) {
        super(idTransaccion, monto, fecha, empleadoGestor, cuentaRegistra);
        this.motivo = motivo;
        this.limiteSuperado = false;
    }

    // Intenta retirar el monto si no supera el límite y hay saldo
    @Override
    public boolean procesar(Cuenta c) {

        // Verifica límite permitido
        if (monto > limite) {
            limiteSuperado = true;
            return false;
        }

        // Se intenta retirar
        boolean ok = c.retirar(monto);

        // Si se retiró correctamente, se registra
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
