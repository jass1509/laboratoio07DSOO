import java.util.ArrayList;

public class Sistema {

    private String nombreSistema;
    private String codigoIdentificacion;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Empleado> listaEmpleados;

    public Sistema(String nombreSistema, String codigoIdentificacion) {
        this.nombreSistema = nombreSistema;
        this.codigoIdentificacion = codigoIdentificacion;
        this.listaClientes = new ArrayList<>();
        this.listaEmpleados = new ArrayList<>();
    }

    // Registrar una persona(puede ser cliente o empleado)
    public boolean registrarPersona(Persona p) {
        if (p instanceof Cliente) {
            listaClientes.add((Cliente) p);
            return true;
        } else if (p instanceof Empleado) {
            listaEmpleados.add((Empleado) p);
            return true;
        }
        return false;
    }

    // Buscar persona por nombre o ID
    public Persona buscarPersona(String criterio) {
        for (Cliente c : listaClientes) {
            if (c.getNombreCompleto().equalsIgnoreCase(criterio) ||
                c.getIdCliente().equalsIgnoreCase(criterio)) {
                return c;
            }
        }
        for (Empleado e : listaEmpleados) {
            if (e.getNombreCompleto().equalsIgnoreCase(criterio) ||
                e.getCodigoEmpleado().equalsIgnoreCase(criterio)) {
                return e;
            }
        }
        return null;
    }

    // Crear una cuenta nueva para un cliente
    public Cuenta crearCuenta(Cliente c, String tipo, double saldoInicial) {
        String numeroCuenta = "C" + (int) (Math.random() * 10000);
        Cuenta nuevaCuenta = new Cuenta(numeroCuenta, saldoInicial, tipo);
        Titularidad t = new Titularidad(c, nuevaCuenta, new java.util.Date(), true);
        c.agregarTitularidad(t);
        return nuevaCuenta;
    }

    // Gestionar una transacción
    public boolean gestionarTransaccion(Transaccion t) {
        return t.procesar(t.cuentaRegistra);
    }

    // Generar un reporte básico de tipo solicitado
    public ArrayList<String> generarReporte(String tipo) {
        ArrayList<String> reporte = new ArrayList<>();

        if (tipo.equalsIgnoreCase("clientes")) {
            for (Cliente c : listaClientes) {
                reporte.add(c.toString());
            }
        } else if (tipo.equalsIgnoreCase("empleados")) {
            for (Empleado e : listaEmpleados) {
                reporte.add(e.toString());
            }
        }
        return reporte;
    }

    @Override
    public String toString() {
        return "Sistema: " + nombreSistema +
               " | Código: " + codigoIdentificacion +
               " | Clientes: " + listaClientes.size() +
               " | Empleados: " + listaEmpleados.size();
    }
}

