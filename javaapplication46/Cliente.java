package javaapplication46;
import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Persona {

    private String idCliente;
    private ArrayList<Titularidad> titularidades;

    public Cliente(String nombre, String apellido, String telefono, String email,
                   Date fechaNacimiento, String idCliente) {
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

    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Titularidad t : titularidades) {
            if (t.getCuenta().getNumeroCuenta().equalsIgnoreCase(numeroCuenta)) {
                return t.getCuenta();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String texto = "Cliente: " + getNombreCompleto() + " | ID: " + idCliente;
        if (titularidades.isEmpty()) {
            texto += "\n   Sin cuentas registradas.";
        } else {
            texto += "\n   Cuentas:";
            for (Titularidad t : titularidades) {
                texto += "\n      " + t.getCuenta().toString();
            }
        }
        return texto;
    }
}
