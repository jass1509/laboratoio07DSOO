
import java.time.LocalDate; // Importación necesaria

public class Empleado extends Persona {

    
    //Atributos subclase Empleado
    private String codigoEmpleado;
    // Atributo actualizado: Usando LocalDate en lugar de java.util.Date
    private LocalDate fechaContratacion; 
    private double salario;
    private String departamento;

    
    //Contrstuctor actualizado (usa LocalDate para ambas fechas)
    public Empleado(String nombre, String apellido, String telefono, String email, LocalDate fechaNacimiento, // CAMBIO AQUÍ: LocalDate
                     String codigoEmpleado, LocalDate fechaContratacion, double salario, String departamento) { // CAMBIO AQUÍ: LocalDate

        super(nombre, apellido, telefono, email, fechaNacimiento);
        this.codigoEmpleado = codigoEmpleado;
        this.fechaContratacion = fechaContratacion; // Ya es LocalDate
        this.salario = salario;
        this.departamento = departamento;
    }

    // Por ahora siempre autoriza transacción
    // (Asumimos que Transaccion sigue usando java.util.Date o un tipo compatible)
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
    
    // Puedes agregar un getter para fechaContratacion actualizado:
    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    @Override
    public String toString() {
        return "Empleado: " +
                "codigoEmpleado='" + codigoEmpleado + '\'' +
                "| nombre='" + getNombre() + " " + getApellido() + '\'' +
                "| telefono='" + getTelefono() + '\'' +
                "| email='" + getEmail() + '\'' +
                "| Fecha Contratación='" + fechaContratacion + '\'' + // Mostrar el nuevo tipo
                "| salario=" + salario +
                "| departamento='" + departamento + '\'';
    }

}
