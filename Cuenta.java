import java.util.ArrayList;

public class Cuenta {

    private String numeroCuenta;
    private double saldo;
    private String tipoCuenta;
    private ArrayList<Transaccion> movimientos;
    private String estado; // Por ejemplo si es "Activa", "Congelada"

    public Cuenta(String numeroCuenta, double saldo, String tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.estado = "Activa";
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

    public String getEstado() {
        return estado;
    }

    // Agrega una transacción a la lista de movimientos
    public void addMovimiento(Transaccion t) {
        movimientos.add(t);
    }

    // Devuelve los movimientos registrados
    public ArrayList<Transaccion> consultarMovimientos() {
        return movimientos;
    }

    // Congela la cuenta
    public void congelarCuenta() {
        estado = "Congelada";
    }

    // Deposita dinero en la cuenta
    public void depositar(double monto) {
        if (estado.equals("Activa") && monto > 0) {
            saldo += monto;
        }
    }

    // Retira dinero de la cuenta si hay saldo suficiente
    public boolean retirar(double monto) {
        if (estado.equals("Activa") && monto > 0 && saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cuenta Nº: " + numeroCuenta +
               " | Tipo: " + tipoCuenta +
               " | Saldo: " + saldo +
               " | Estado: " + estado +
               " | Movimientos: " + movimientos.size();
    }
}
