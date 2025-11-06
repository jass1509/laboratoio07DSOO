package javaapplication46;
import java.util.Date;

public class Titularidad {

    private Date fechaInicio;
    private boolean esPrincipal;
    private Cliente clienteAsociado;
    private Cuenta cuentaAsociada;

    public Titularidad(Cliente clienteAsociado, Cuenta cuentaAsociada, Date fechaInicio, boolean esPrincipal) {

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

    public Date getFechaInicio() {
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
