import java.time.LocalDate;

/**
 * Clase que representa a un Administrador del sistema.
 * Hereda de persona y tiene un nivel de acceso especial para gestión global.
 */
public class Administrador extends Persona {
    
    private String idAdmin;
    private int nivelAcceso; // 1: Super Admin, 2: Admin de Sucursal, etc.

    public Administrador(String nombre, String apellido, String telefono, String email, 
                         LocalDate fechaNacimiento, String idAdmin, int nivelAcceso) {
        super(nombre, apellido, telefono, email, fechaNacimiento);
        this.idAdmin = idAdmin;
        this.nivelAcceso = nivelAcceso;
    }

    // Getters
    public String getIdAdmin() {
        return idAdmin;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    // Métodos específicos del Administrador (ejemplos de sus capacidades)
    public void crearUsuario(Persona nuevoUsuario) {
        System.out.println("Administrador " + getNombreCompleto() + " ha creado un nuevo usuario.");
    }

    public void eliminarUsuario(Persona usuario) {
        System.out.println("Administrador " + getNombreCompleto() + " ha eliminado al usuario: " + usuario.getNombreCompleto());
    }

    @Override
    public String toString() {
        return "ADMINISTRADOR | ID: " + idAdmin + " | Nivel: " + nivelAcceso + 
               " | Nombre: " + getNombreCompleto() + " | Email: " + email;
    }
}