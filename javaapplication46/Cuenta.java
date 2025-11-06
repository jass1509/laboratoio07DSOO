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

    
    //Getters y setters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    
    //Añadiendo saldo 
    public void depositar(double monto) {
        saldo += monto;
    }

    
    //Quitando saldo
    public boolean retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    
    //Añadiendo el movimiento al registro
    public void addMovimiento(Transaccion t) {
        movimientos.add(t);
    }

    
    //Impresión de todos los movimientos
    public ArrayList<Transaccion> consultarMovimientos() {
        return movimientos;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Cuenta " + numeroCuenta + " | Tipo: " + tipoCuenta + " | Saldo: " + saldo;
    }
}
