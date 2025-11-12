
import java.time.LocalDateTime; // Clase moderna para la fecha y hora de la transacción

public class Retiro extends Transaccion {

    //Atributos con restricciones de saldo ingresado
    private boolean limiteSuperado;
    private double limite = 1000; // Límite simple permitido

    // Constructor actualizado: La fecha es ahora LocalDateTime
    public Retiro(String idTransaccion, double monto, LocalDateTime fecha, Empleado empleadoGestor,
                  Cuenta cuentaRegistra, String motivo) {
        // Llama al constructor de la superclase (Transaccion), que ahora acepta LocalDateTime
        super(idTransaccion, monto, fecha, empleadoGestor, cuentaRegistra, motivo); 
        this.limiteSuperado = false;
    }

    
    //Polimorfismo
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
        return "[RETIRO] Código: " + codigo +
                " | Monto: " + monto +
                " | Fecha: " + fecha + // 'fecha' es ahora LocalDateTime
                " | Cuenta: " + cuentaRegistra.getNumeroCuenta() +
                " | Motivo: " + motivo +
                " | Límite superado: " + limiteSuperado;
    }
}
