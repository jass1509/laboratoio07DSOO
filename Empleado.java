package javaapplication46;
public class Empleado extends Persona {
    private String idEmpleado;
    private String cargo;

    public void procesarTransaccion(Transaccion t) {
        t.procesar();
    }

    public void registrarCliente(Cliente c) {
        // lógica para registrar cliente
    }
}
