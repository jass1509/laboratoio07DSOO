package javaapplication46;
import java.util.ArrayList;

import java.util.Date;
import java.util.Scanner;

import PaqueteClases.Sistema;

public class MenuSistema {

	
	//Atributos para un objeto cualquiera para empezar el programa
    private Sistema sistema;
    private Scanner sc;
    private Empleado empleadoActivo;

    
    //Creacion de datos inicializados
    public MenuSistema() {
        //Creando objeto sistema
    		this.sistema = new Sistema("BANCO BCP", "CV2");
		this.sc = new Scanner(System.in);
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

    
    //Muestra todos los empleados
    public void verEmpleados() {
        System.out.println("\n-- Lista de Empleados --");
        ArrayList<String> empleados = sistema.generarReporte("empleados");
        for (String info : empleados) {
            System.out.println(info);
        }
    }

    //Ingresa un empleado con sus propiedades por ingreso de datos
    public void agregarEmpleado() {
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

    
    //Registro de cliente por ingreso de datos
    public void agregarCliente() {
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

    
    //Muestra todos los clientes registrados
    public void verClientes() {
        System.out.println("\n-- Lista de Clientes --");
        ArrayList<String> clientes = sistema.generarReporte("clientes");
        for (String info : clientes) {
            System.out.println(info);
        }
    }

    //Creación básicade una cuenta en un banco
    public void crearCuenta() {
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

    
    //Muestra todas las cuentas creadas en el banco
    public void verCuentasCliente() {
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

    
    //Metodo de peticion de deposito
    public void hacerDeposito() {
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

    //Metodo para la configuracion de un retiro, verifica condiciones necesarias
    public void hacerRetiro() {
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

    
    //Muestra todos los movimientos
    public void verMovimientos() {
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

    
    //Verificacion de datos correctos con atrape de error
    public int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    public double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un valor numérico válido: ");
            }
        }
    }
}
