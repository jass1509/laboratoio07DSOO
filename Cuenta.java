package javaapplication46;
import java.util.Date;
import java.util.List;

public class Cuenta {
    private String numeroCuenta;
    private String tipoCuenta; // Ahorros, Corriente, etc.
    private double saldo;
    private Date fechaApertura;
    private List<Transaccion> transacciones;

    public void depositar(double monto) {
        saldo += monto;
    }

    public void retirar(double monto) {
        saldo -= monto;
    }

    public double consultarSaldo() {
        return saldo;
    }
}
