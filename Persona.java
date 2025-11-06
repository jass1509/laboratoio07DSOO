import java.util.Date;

public class Persona {

    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String email;
    protected Date fechaNacimiento;

    public Persona(String nombre, String apellido, String telefono, String email, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Retorna nombre completo //
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    // Actualiza teléfono y correo //
    public void actualizarContacto(String telefono, String email) {
        this.telefono = telefono;
        this.email = email;
    }

    // Calcula edad aproximada según año //
    public int calcularEdad() {
        Date hoy = new Date();
        return hoy.getYear() - fechaNacimiento.getYear();
    }
}
