package javaapplication46;
import java.util.Date;

public class Deposito extends Transaccion {

    public Deposito(String codigo, double monto, Date fecha, Empleado empleado, Cuenta cuenta, String motivo) {
        super(codigo, monto, fecha, empleado, cuenta, motivo);
    }

    @Override
    public boolean procesar(Cuenta cuentaRegistra) {
        cuentaRegistra.depositar(monto);
        cuentaRegistra.addMovimiento(this);
        return true;
    }

    @Override
    public String toString() {
        return "[DEPÓSITO] Código: " + codigo +
                " | Monto: " + monto +
                " | Fecha: " + fecha +
                " | Cuenta: " + cuentaRegistra.getNumeroCuenta() +
                " | Motivo: " + motivo;
    }
}
