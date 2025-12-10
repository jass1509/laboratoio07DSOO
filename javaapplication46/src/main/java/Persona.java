import java.time.LocalDate;
import java.time.Period; // Necesario para calcular la diferencia de tiempo

public class Persona {

    // Atributos superclase persona
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String email;
    // Atributo actualizado: Usando LocalDate en lugar de java.util.Date
    protected LocalDate fechaNacimiento; 

    public Persona(String nombre, String apellido, String telefono, String email, LocalDate fechaNacimiento) {
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

    // Calcula edad aproximada usando la nueva API java.time
    public int calcularEdad() {
        // LocalDate.now() obtiene la fecha actual
        LocalDate hoy = LocalDate.now(); 
        
        // Period.between() calcula la diferencia entre dos LocalDates en años, meses y días
        // Luego usamos getYears() para obtener solo la parte de los años.
        return Period.between(fechaNacimiento, hoy).getYears();
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

    // Getter actualizado para devolver LocalDate
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
}
