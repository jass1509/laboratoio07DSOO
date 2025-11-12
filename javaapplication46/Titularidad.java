
import java.time.LocalDate; // Clase moderna para manejar solo la fecha

public class Titularidad {

    // Atributo actualizado: Usando LocalDate en lugar de java.util.Date
    private LocalDate fechaInicio; 
    private boolean esPrincipal;
    private Cliente clienteAsociado;
    private Cuenta cuentaAsociada;

    // Constructor actualizado: La fecha de inicio es ahora LocalDate
    public Titularidad(Cliente clienteAsociado, Cuenta cuentaAsociada, LocalDate fechaInicio, boolean esPrincipal) {

      // clienteAsociado y cuentaAsociada enlazan las clases
        this.clienteAsociado = clienteAsociado;
        this.cuentaAsociada = cuentaAsociada;
        this.fechaInicio = fechaInicio;
        this.esPrincipal = esPrincipal;
    }

    
    //Getters y setters
    public Cliente getCliente() {
        return clienteAsociado;
    }

    public Cuenta getCuenta() {
        return cuentaAsociada;
    }

    // Getter actualizado: Retorna LocalDate
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    // Indica si es el titular principal
    public boolean isPrincipal() {
        return esPrincipal;
    }

    @Override
    public String toString() {
        return "Titularidad | Cliente: " + clienteAsociado.getNombreCompleto() +
               " | Cuenta: " + cuentaAsociada.getNumeroCuenta() +
               " | Principal: " + esPrincipal +
               " | Fecha inicio: " + fechaInicio;
    }
}
