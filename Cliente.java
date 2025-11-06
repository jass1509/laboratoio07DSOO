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

    // Busca una cuenta por su número
    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Titularidad t : titularidades) {
            Cuenta c = t.getCuenta();
            if (c.getNumeroCuenta().equals(numeroCuenta)) {
                return c;
            }
        }
        return null; // si no la encuentra
    }

    // Transfiere dinero entre cuentas (básico)
    public boolean transferir(Cuenta destino, double monto) {
        for (Titularidad t : titularidades) {
            Cuenta origen = t.getCuenta();
            if (origen.retirar(monto)) {
                destino.depositar(monto);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombreCompleto() + " | ID: " + idCliente + 
               " | Nº de cuentas: " + titularidades.size();
    }
}
