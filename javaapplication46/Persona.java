package javaapplication46;
import java.util.Date;

public class Persona {

	//Atributos superclase persona
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String email;
    protected Date fechaNacimiento;

    //Constructor
    public Persona(String nombre, String apellido, String telefono, String email, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Retorna nombre completo
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    // Actualiza datos de contacto
    public void actualizarContacto(String telefono, String email) {
        this.telefono = telefono;
        this.email = email;
    }

    // Calcula edad aproximada
    public int calcularEdad() {
        Date hoy = new Date();
        int años = hoy.getYear() - fechaNacimiento.getYear();

        // Ajuste si aún no cumplió años este año
        if (hoy.getMonth() < fechaNacimiento.getMonth()
                || (hoy.getMonth() == fechaNacimiento.getMonth() && hoy.getDate() < fechaNacimiento.getDate())) {
            años--;
        }

        return años;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
}
