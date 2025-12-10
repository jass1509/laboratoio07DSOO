import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestConexion {
    public static void main(String[] args) {
        // 1️⃣ Conexión a la base de datos
        if (ConexionBD.conectar() == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return;
        }

        // ---------------------------
        // 2️⃣ Crear e insertar un cliente
        Cliente cliente = new Cliente(
            "Juan",
            "Pérez",
            "987654321",
            "juan@mail.com",
            LocalDate.of(1990, 1, 1),
            "C001",
            "1234"
        );
        ClienteDAO clienteDAO = new ClienteDAO();
        if (clienteDAO.insertar(cliente)) {
            System.out.println("Cliente insertado correctamente.");
        } else {
            System.out.println("Error al insertar cliente.");
        }

        // ---------------------------
        // 3️⃣ Crear e insertar un empleado
        Empleado empleado = new Empleado(
            "Ana",
            "García",
            "987654322",
            "ana@mail.com",
            LocalDate.of(1985, 5, 15),
            "E001",
            LocalDate.of(2020, 1, 10),
            3500.0,
            "Finanzas",
            "4321"
        );
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        if (empleadoDAO.insertar(empleado)) {
            System.out.println("Empleado insertado correctamente.");
        } else {
            System.out.println("Error al insertar empleado.");
        }

        // ---------------------------
        // 4️⃣ Crear e insertar una cuenta para el cliente
        Cuenta cuenta = new Cuenta(
            "CU001",
            1000.0,
            "Ahorros"
        );
        // Vinculamos la cuenta con el cliente en memoria (titularidad)
        cliente.agregarTitularidad(new Titularidad(cliente, cuenta, LocalDate.now(), true));

        CuentaDAO cuentaDAO = new CuentaDAO();
        if (cuentaDAO.insertar(cuenta, cliente.getIdCliente())) {
            System.out.println("Cuenta insertada correctamente.");
        } else {
            System.out.println("Error al insertar cuenta.");
        }

        // ---------------------------
        // 5️⃣ Crear e insertar una transacción (depósito)
        Transaccion deposito = new Deposito(
            "T001",
            500.0,
            LocalDateTime.now(),
            empleado,
            cuenta,
            "Depósito inicial"
        );

        TransaccionDAO transaccionDAO = new TransaccionDAO();
        if (transaccionDAO.registrar(deposito)) {
            System.out.println("Transacción registrada correctamente.");
        } else {
            System.out.println("Error al registrar transacción.");
        }

        // ---------------------------
        // 6️⃣ Listar todos los clientes
        System.out.println("\nListado de clientes:");
        for (Cliente c : clienteDAO.listar()) {
            System.out.println(c);
        }

        // 7️⃣ Listar todas las cuentas del cliente
        System.out.println("\nCuentas de " + cliente.getNombreCompleto() + ":");
        for (Cuenta cu : cuentaDAO.listarPorCliente(cliente.getIdCliente())) {
            System.out.println(cu);
        }

        // 8️⃣ Listar transacciones de la cuenta
        System.out.println("\nTransacciones de la cuenta " + cuenta.getNumeroCuenta() + ":");
        for (String t : transaccionDAO.listar(cuenta.getNumeroCuenta())) {
            System.out.println(t);
        }
    }
}

