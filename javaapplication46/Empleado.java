import java.time.LocalDate;

public class Empleado extends Persona {

    private String codigoEmpleado;
    private LocalDate fechaContratacion;
    private double salario;
    private String departamento;
    private String pin; // Nuevo atributo

    public Empleado(String nombre, String apellido, String telefono, String email, LocalDate fechaNacimiento,
                    String codigoEmpleado, LocalDate fechaContratacion, double salario, String departamento, String pin) {
        super(nombre, apellido, telefono, email, fechaNacimiento);
        this.codigoEmpleado = codigoEmpleado;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
        this.departamento = departamento;
        this.pin = pin;
    }

    public boolean autorizarTransaccion(Transaccion t) {
        return true;
    }

    public String auditarCuenta(Cuenta c) {
        return "Cuenta: " + c.getNumeroCuenta() + " | Saldo: " + c.getSaldo();
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public String toString() {
        return "Empleado: " +
                "codigoEmpleado='" + codigoEmpleado + '\'' +
                "| nombre='" + getNombre() + " " + getApellido() + '\'' +
                "| telefono='" + getTelefono() + '\'' +
                "| email='" + getEmail() + '\'' +
                "| Fecha Contratación='" + fechaContratacion + '\'' +
                "| salario=" + salario +
                "| departamento='" + departamento + '\'' +
                "| PIN='" + pin + '\'';
    }
}
