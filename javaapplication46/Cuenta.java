package javaapplication46;
import java.util.ArrayList;

public class Cuenta {

    private String numeroCuenta;
    private double saldo;
    private String tipoCuenta;
    private ArrayList<Transaccion> movimientos;

    public Cuenta(String numeroCuenta, double saldo, String tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.movimientos = new ArrayList<>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public boolean retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public void addMovimiento(Transaccion t) {
        movimientos.add(t);
    }

    public ArrayList<Transaccion> consultarMovimientos() {
        return movimientos;
    }

    @Override
    public String toString() {
        return "Cuenta " + numeroCuenta + " | Tipo: " + tipoCuenta + " | Saldo: " + saldo;
    }
}
