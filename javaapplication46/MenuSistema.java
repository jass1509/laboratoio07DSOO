package javaapplication46;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MenuSistema {

    private Sistema sistema;
    private Scanner sc;
    private Empleado empleadoActivo;

    public MenuSistema() {
        sc = new Scanner(System.in);
        sistema = new Sistema("Banco Pichincha", "SYS001");

        // Inicialización de ejemplo
        Empleado emp1 = new Empleado("Carlos", "Pérez", "912067512", "carlosP@gmail.com",
                new Date(90, 5, 12), "E001", new Date(), 2500, "Cajas");
        sistema.registrarPersona(emp1);
        empleadoActivo = emp1; // empleado activo por defecto

        Empleado emp2 = new Empleado("Jose", "Zimmerman", "994453665", "joseZ@gmail.com",
                new Date(85, 7, 28), "E002", new Date(), 3500, "Administración");
        sistema.registrarPersona(emp2);

        Cliente cli1 = new Cliente("Ana", "Zapana", "956564871", "anaZ@gmail.com",
                new Date(95, 3, 25), "C001");
        sistema.registrarPersona(cli1);
        sistema.crearCuenta(cli1, "Ahorros", 1000);

        Cliente cli2 = new Cliente("Julio", "Mamani", "944575848", "julioM@gmail.com",
                new Date(92, 7, 17), "B034");
        sistema.registrarPersona(cli2);
        sistema.crearCuenta(cli2, "Corriente", 2000);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ DEL SISTEMA =====");
            System.out.println("1. Ver empleados");
            System.out.println("2. Agregar empleado");
            System.out.println("3. Agregar cliente");
            System.out.println("4. Ver clientes");
            System.out.println("5. Crear cuenta");
            System.out.println("6. Ver cuentas de un cliente");
            System.out.println("7. Hacer depósito");
            System.out.println("8. Hacer retiro");
            System.out.println("9. Ver movimientos");
            System.out.println("10. Salir");
            System.out.print("Elija una opción: ");
            opcion = leerEntero();

           switch (opcion) {
    case 1:
        verEmpleados();
        break;
    case 2:
        agregarEmpleado();
        break;
    case 3:
        agregarCliente();
        break;
    case 4:
        verClientes();
        break;
    case 5:
        crearCuenta();
        break;
    case 6:
        verCuentasCliente();
        break;
    case 7:
        hacerDeposito();
        break;
    case 8:
        hacerRetiro();
        break;
    case 9:
        verMovimientos();
        break;
    case 10:
        System.out.println("Saliendo del sistema...");
        break;
    default:
        System.out.println("Opción inválida.");
        break;
}

        } while (opcion != 10);
    }

    private void verEmpleados() {
        System.out.println("\n-- Lista de Empleados --");
        ArrayList<String> empleados = sistema.generarReporte("empleados");
        for (String info : empleados) {
            System.out.println(info);
        }
    }

    private void agregarEmpleado() {
        System.out.println("\n-- Registro de nuevo empleado --");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Código empleado: ");
        String codigo = sc.nextLine();
        System.out.print("Salario: ");
        double salario = leerDouble();
        System.out.print("Departamento: ");
        String dep = sc.nextLine();

        Empleado nuevo = new Empleado(nombre, apellido, telefono, email,
                new Date(95, 0, 1), codigo, new Date(), salario, dep);

        sistema.registrarPersona(nuevo);
        System.out.println("Empleado agregado correctamente.");
    }

    private void agregarCliente() {
        System.out.println("\n-- Registro de nuevo cliente --");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("ID cliente: ");
        String id = sc.nextLine();

        Cliente nuevo = new Cliente(nombre, apellido, telefono, email, new Date(95, 0, 1), id);
        sistema.registrarPersona(nuevo);
        System.out.println("Cliente agregado correctamente.");
    }

    private void verClientes() {
        System.out.println("\n-- Lista de Clientes --");
        ArrayList<String> clientes = sistema.generarReporte("clientes");
        for (String info : clientes) {
            System.out.println(info);
        }
    }

    private void crearCuenta() {
        System.out.print("Ingrese ID del cliente: ");
        String id = sc.nextLine();
        Persona p = sistema.buscarPersona(id);
        if (p instanceof Cliente) {
            Cliente c = (Cliente) p;
            System.out.print("Tipo de cuenta: ");
            String tipo = sc.nextLine();
            System.out.print("Saldo inicial: ");
            double saldo = leerDouble();
            Cuenta nueva = sistema.crearCuenta(c, tipo, saldo);
            System.out.println("Cuenta creada correctamente. Número: " + nueva.getNumeroCuenta());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void verCuentasCliente() {
        System.out.print("Ingrese ID del cliente: ");
        String id = sc.nextLine();
        Persona p = sistema.buscarPersona(id);
        if (p instanceof Cliente) {
            Cliente c = (Cliente) p;
            System.out.println("\n-- Cuentas de " + c.getNombreCompleto() + " --");
            for (Titularidad t : c.getTitularidades()) {
                Cuenta cuenta = t.getCuenta();
                System.out.println("Número: " + cuenta.getNumeroCuenta() + " | Tipo: " +
                        cuenta.getTipoCuenta() + " | Saldo: " + cuenta.getSaldo());
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void hacerDeposito() {
        System.out.print("Ingrese ID del cliente: ");
        String id = sc.nextLine();
        Persona p = sistema.buscarPersona(id);
        if (p instanceof Cliente) {
            Cliente c = (Cliente) p;
            System.out.print("Número de cuenta: ");
            String num = sc.nextLine();
            Cuenta cuenta = c.buscarCuenta(num);
            if (cuenta != null) {
                System.out.print("Monto: ");
                double monto = leerDouble();
                System.out.print("Motivo: ");
                String motivo = sc.nextLine();
                Transaccion dep = new Deposito("T" + System.currentTimeMillis(), monto,
                        new Date(), empleadoActivo, cuenta, motivo);
                sistema.gestionarTransaccion(dep);
                System.out.println("Depósito realizado con éxito.");
            } else {
                System.out.println("Cuenta no encontrada.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void hacerRetiro() {
        System.out.print("Ingrese ID del cliente: ");
        String id = sc.nextLine();
        Persona p = sistema.buscarPersona(id);
        if (p instanceof Cliente) {
            Cliente c = (Cliente) p;
            System.out.print("Número de cuenta: ");
            String num = sc.nextLine();
            Cuenta cuenta = c.buscarCuenta(num);
            if (cuenta != null) {
                System.out.print("Monto: ");
                double monto = leerDouble();
                System.out.print("Motivo: ");
                String motivo = sc.nextLine();
                Transaccion ret = new Retiro("T" + System.currentTimeMillis(), monto,
                        new Date(), empleadoActivo, cuenta, motivo);
                boolean ok = sistema.gestionarTransaccion(ret);
                if (ok) System.out.println("Retiro exitoso.");
                else System.out.println("No se pudo realizar el retiro (límite o saldo insuficiente).");
            } else {
                System.out.println("Cuenta no encontrada.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void verMovimientos() {
        System.out.print("Ingrese ID del cliente: ");
        String id = sc.nextLine();
        Persona p = sistema.buscarPersona(id);
        if (p instanceof Cliente) {
            Cliente c = (Cliente) p;
            for (Titularidad t : c.getTitularidades()) {
                System.out.println("\nMovimientos de la cuenta " + t.getCuenta().getNumeroCuenta() + ":");
                for (Transaccion tr : t.getCuenta().consultarMovimientos()) {
                    System.out.println(tr);
                }
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un valor numérico válido: ");
            }
        }
    }
}
