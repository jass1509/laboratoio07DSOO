import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    private String IDCliente;
    private List<Titularidad> titularidades; // Es para la relación con sus cuentas

    public Cliente(String nombre, String apellido, String telefono, String email, Date fechaNacimiento,
                   String IDCliente) {

        super(nombre, apellido, telefono, email, fechaNacimiento);
        this.IDCliente = IDCliente;
        this.titularidades = new ArrayList<>();
    }

    // Retorna el ID de cliente
    public String getIDCliente() {
        return IDCliente;
    }

    // Agrega una titularidad(asocia la cuenta)
    public void agregarTitularidad(Titularidad t) {
        titularidades.add(t);
    }

    // Busca una cuenta por número
    public Cuenta buscarCuenta(String numero) {
        for (Titularidad t : titularidades) {
            Cuenta c = t.getCuenta();
            if (c.getNumeroCuenta().equals(numero)) {
                return c;
            }
        }
        return null; // "null" si no encuentra la cuenta
    }

    // Retorna lista de transacciones por criterio simple de texto
    public List<Transaccion> consultarMovimientos(String criterio) {
        List<Transaccion> resultado = new ArrayList<>();

        for (Titularidad t : titularidades) {
            Cuenta c = t.getCuenta();
            for (Transaccion mov : c.consultarMovimientos()) {
                // Se filtra por texto en el toString() de la transacción
                if (mov.toString().contains(criterio)) {
                    resultado.add(mov);
                }
            }
        }

        return resultado;
    }

    // Transfiere dinero entre cuentas
    public boolean transferir(Cuenta destino, double monto) {

        // Necesitamos obtener la cuenta origen(principalmente se usa la primera)
        if (titularidades.isEmpty()) return false;

        Cuenta origen = titularidades.get(0).getCuenta();

        // Intentar retirar del origen
        if (origen.retirar(monto)) {
            // Depositar en destino
            destino.depositar(monto);
            // Registrar movimientos en ambas cuentas
            origen.addMovimiento(new Transaccion("T-INT", monto, new Date(), null, origen) {
                @Override
                public boolean procesar(Cuenta c) { return true; }
                @Override
                public String toString() { return "Transferencia Saliente | Monto: " + monto; }
            });

            destino.addMovimiento(new Transaccion("T-INT", monto, new Date(), null, destino) {
                @Override
                public boolean procesar(Cuenta c) { return true; }
                @Override
                public String toString() { return "Transferencia Entrante | Monto: " + monto; }
            });

            return true;
        }

        return false; // Si no se pudo transferir retorna false
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombreCompleto() + " | ID: " + IDCliente;
    }
}
