package javaapplication46;
import java.util.Date;

public class Empleado extends Persona {

    private String codigoEmpleado;
    private Date fechaContratacion;
    private double salario;
    private String departamento;

    public Empleado(String nombre, String apellido, String telefono, String email, Date fechaNacimiento,
                    String codigoEmpleado, Date fechaContratacion, double salario, String departamento) {

        super(nombre, apellido, telefono, email, fechaNacimiento);
        this.codigoEmpleado = codigoEmpleado;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
        this.departamento = departamento;
    }

    // Por ahora siempre autoriza transacción
    public boolean autorizarTransaccion(Transaccion t) {
        return true;
    }

    // Información simple de auditoría
    public String auditarCuenta(Cuenta c) {
        return "Cuenta: " + c.getNumeroCuenta() + " | Saldo: " + c.getSaldo();
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }
    @Override
    public String toString() {
        return "Empleado: " +
                "codigoEmpleado='" + codigoEmpleado + '\'' +
                "| nombre='" + getNombre() + " " + getApellido() + '\'' +
                "| telefono='" + getTelefono() + '\'' +
                "| email='" + getEmail() + '\'' +
                "| salario=" + salario +
                "| departamento='" + departamento + '\'';
    }

}
