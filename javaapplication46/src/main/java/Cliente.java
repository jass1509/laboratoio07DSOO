
import java.time.LocalDate;
import java.util.ArrayList; // Importación necesaria

public class Cliente extends Persona {
    
    
    //Atributos subclase cliente
    private String idCliente;
    private String pin; // NUEVO PIN, para el cajero
    private ArrayList<Titularidad> titularidades;

    
    //Constructor cliente actualizado (añadir PIN)
    public Cliente(String nombre, String apellido, String telefono, String email,
                    LocalDate fechaNacimiento, String idCliente, String pin) { // se añade PIN en el constructor
        super(nombre, apellido, telefono, email, fechaNacimiento);
        this.idCliente = idCliente;
        this.pin = pin; // PIN
        this.titularidades = new ArrayList<>();
    }

    //Getters
    public String getIdCliente() {
        return idCliente;
    }

    public String getPin() {
        return pin;
    }

    //Añadiendo titularidades en lista
    public void agregarTitularidad(Titularidad t) {
        titularidades.add(t);
    }
    
    //ArrayList de titularidades
    public ArrayList<Titularidad> getTitularidades() {
        return titularidades;
    }

    
    //Mostrar cuentas
    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Titularidad t : titularidades) {
            if (t.getCuenta().getNumeroCuenta().equalsIgnoreCase(numeroCuenta)) {
                return t.getCuenta();
            }
        }
        return null;
    }

    
    //Metodo toString
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
