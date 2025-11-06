package javaapplication46;
public class Persona {
private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    public void mostrarDatos() {
        System.out.println(nombre + " " + apellido + " - DNI: " + dni);
    }
    
}
