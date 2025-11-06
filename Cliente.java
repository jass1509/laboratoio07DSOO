import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Persona {

    private String idCliente;
    private ArrayList<Titularidad> titularidades;

    public Cliente(String nombre, String apellido, String telefono, String email, Date fechaNacimiento, String idCliente) {
        super(nombre, apellido, telefono, email, fechaNacimiento);
        this.idCliente = idCliente;
        this.titularidades = new ArrayList<>();
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void agregarTitularidad(Titularidad t) {
        titularidades.add(t);
    }

    public ArrayList<Titularidad> getTitularidades() {
        return titularidades;
    }

    // Buscar una cuenta por su número
    public Cuenta buscarCuenta(String numero) {
        for (Titularidad t : titularidades) {
            if (t.getCuenta().getNumeroCuenta().equalsIgnoreCase(numero)) {
                return t.getCuenta();
            }
        }
        return null;
    }

    // Consultar movimientos de todas sus cuentas
    public ArrayList<Transaccion> consultarMovimientos(String criterio) {
        ArrayList<Transaccion> lista = new ArrayList<>();
        for (Titularidad t : titularidades) {
            for (Transaccion tr : t.getCuenta().consultarMovimientos()) {
                if (tr.toString().contains(criterio)) {
                    lista.add(tr);
                }
            }
        }
        return lista;
    }

    // Transferir dinero a otra cuenta
    public boolean transferir(Cuenta destino, double monto) {
        for (Titularidad t : titularidades) {
            Cuenta origen = t.getCuenta();
            if (origen.getSaldo() >= monto) {
                origen.retirar(monto);
                destino.depositar(monto);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombreCompleto() + " | ID: " + idCliente + " | Cuentas: " + titularidades.size();
    }
}
