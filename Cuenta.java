import java.util.ArrayList;
import java.util.List;

// Clase Cuenta bancaria
public class Cuenta {

    private String numeroCuenta;
    private double saldo;
    private String tipoCuenta;      // Ejemplo: "Ahorros", "Corriente"
    private List<Transaccion> movimientos;
    private String estado;          // Ejemplo: "Activa", "Congelada"

    // Constructor
    public Cuenta(String numeroCuenta, double saldoInicial, String tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.tipoCuenta = tipoCuenta;
        this.movimientos = new ArrayList<>();
        this.estado = "Activa"; // Por defecto la cuenta está activa
    }

    // Retorna el número de cuenta
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    // Retorna el saldo actual
    public double getSaldo() {
        return saldo;
    }

    // Agrega un movimiento al historial
    public void addMovimiento(Transaccion t) {
        movimientos.add(t);
    }

    // Lista todos los movimientos
    public List<Transaccion> consultarMovimientos() {
        return movimientos;
    }

    // Cambia estado de la cuenta
    public void congelarCuenta() {
        this.estado = "Congelada";
    }

    // Deposita dinero
    public void depositar(double monto) {
        if (estado.equals("Activa")) {
            saldo += monto;
        }
    }

    // Retira dinero si hay fondos disponibles
    public boolean retirar(double monto) {
        if (estado.equals("Activa") && saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cuenta: " + numeroCuenta +
                " | Tipo: " + tipoCuenta +
                " | Saldo: " + saldo +
                " | Estado: " + estado;
    }
}
