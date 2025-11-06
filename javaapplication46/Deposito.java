package javaapplication46;
import java.util.Date;

public class Deposito extends Transaccion {
	/*La clase Deposito extiende a la clase Transaccion, lo que significa
	/que hereda todos los atributos y métodos públicos y protegidos de Transaccion.
	/ Deposito es una 'Transaccion' especializada.*/
	
    public Deposito(String codigo, double monto, Date fecha, Empleado empleado, Cuenta cuenta, String motivo) {
        super(codigo, monto, fecha, empleado, cuenta, motivo);
    }

    //Polimorfismo
    @Override
    public boolean procesar(Cuenta cuentaRegistra) {
        cuentaRegistra.depositar(monto);
        cuentaRegistra.addMovimiento(this);
        return true;
    }

    
    //Metodo string
    @Override
    public String toString() {
        return "[DEPÓSITO] Código: " + codigo +
                " | Monto: " + monto +
                " | Fecha: " + fecha +
                " | Cuenta: " + cuentaRegistra.getNumeroCuenta() +
                " | Motivo: " + motivo;
    }
}
