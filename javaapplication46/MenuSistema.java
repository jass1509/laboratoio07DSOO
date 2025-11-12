
import java.time.LocalDate;
import java.time.LocalDateTime;     // Importamos LocalDate para fechas
import java.util.ArrayList; //mportamos LocalDateTime para transacciones
import java.util.Scanner;
// Se elimina la importación de java.util.Date

public class MenuSistema {

    
    //Atributos para un objeto cualquiera para empezar el programa
    private Sistema sistema;
    private Scanner sc;
    private Empleado empleadoActivo;
    private Empleado empleadoATM;

    
    //Creacion de datos inicializados
    public MenuSistema() {
        //Creando objeto sistema
            this.sistema = new Sistema("BANCO BCP", "CV2");
        this.sc = new Scanner(System.in);
            
        // Empleado 1
        Empleado emp1 = new Empleado("Carlos", "Pérez", "912067512", "carlosP@gmail.com",
                LocalDate.of(1990, 6, 12), "E001", LocalDate.now(), 2500.0, "Cajas"); 
        sistema.registrarPersona(emp1);
        empleadoActivo = emp1; // empleado activo por defecto

        // Empleado 2
        Empleado emp2 = new Empleado("Jose", "Zimmerman", "994453665", "joseZ@gmail.com",
                LocalDate.of(1985, 8, 28), "E002", LocalDate.now(), 3500.0, "Administración"); 
        sistema.registrarPersona(emp2);

        // nuevo empleado para transacciones de Cajero Automático (No humano) ---
        this.empleadoATM = new Empleado("Sistema", "ATM", "N/A", "atm@sistema.com",
                                        LocalDate.of(2023, 1, 1), "EATM", LocalDate.now(), 0.0, "AUTOMÁTICO");
        sistema.registrarPersona(empleadoATM); // Se registra, aunque no sea un empleado humano

        // Cliente 1
        Cliente cli1 = new Cliente("Ana", "Zapana", "956564871", "anaZ@gmail.com",LocalDate.of(1995, 4, 25),"C001", "1234");
        sistema.registrarPersona(cli1);
        sistema.crearCuenta(cli1, "Ahorros", 1000);

        // Cliente 2
        Cliente cli2 = new Cliente("Julio", "Mamani", "944575848", "julioM@gmail.com",LocalDate.of(1992, 8, 17), "B034", "5678");
        sistema.registrarPersona(cli2);
        sistema.crearCuenta(cli2, "Corriente", 2000);
        
    }
    public Sistema getSistema() {
        return sistema;
    }
    
    public Empleado getEmpleadoATM() {
        return empleadoATM;
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
    // Uso de validación de Nombre (solo letras)
    String nombre = GestorValidaciones.verificarLetras(sc, "Nombre: ");
    // Uso de validación de Apellido (solo letras)
    String apellido = GestorValidaciones.verificarLetras(sc, "Apellido: ");
    // Uso de validación de Teléfono (9 dígitos)
    String telefono = GestorValidaciones.verificarNumeros(sc, "Teléfono: ");

    String email = GestorValidaciones.verificarCorreo(sc, "Email: ");
    
    // Validacion e ingreso de los datos de la fecha de nacimiento
    System.out.println("--- Ingrese Fecha de Nacimiento ---");
    // Uso de validación para la fecha de nacimiento (YYYY, MM, DD)
    int anio = GestorValidaciones.validarAnio(sc, "Año (YYYY): "); 
    int mes = GestorValidaciones.validarMes(sc, "Mes (1-12): ");
    int dia = GestorValidaciones.validarDia(sc, "Día (1-31): ");
    
    // Creamos el objeto LocalDate con los datos validados
    LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia); 

    System.out.print("Código empleado: ");
    String codigo = sc.nextLine();
    System.out.print("Salario: ");
    double salario = GestorValidaciones.validarMonto(sc);
    System.out.print("Departamento: ");
    String dep = sc.nextLine();

    Empleado nuevo = new Empleado(nombre, apellido, telefono, email,
            fechaNacimiento, codigo, LocalDate.now(), salario, dep); // LocalDate.now() se mantiene para la fecha de contratación

    sistema.registrarPersona(nuevo);
    System.out.println("Empleado agregado correctamente.");
}

    
    //Registro de cliente por ingreso de datos
    public void agregarCliente() {
        System.out.println("\n-- Registro de nuevo cliente --");
        // Uso de validación: Nombre (solo letras)
        String nombre = GestorValidaciones.verificarLetras(sc, "Nombre: ");
        // Uso de validación: Apellido (solo letras)
        String apellido = GestorValidaciones.verificarLetras(sc, "Apellido: ");
        // Uso de validación: Teléfono (9 dígitos)
        String telefono = GestorValidaciones.verificarNumeros(sc, "Teléfono: ");
        // Uso de validación: Email
        String email = GestorValidaciones.verificarCorreo(sc, "Email: ");
        
        System.out.print("ID cliente: ");
        String id = sc.nextLine();

        System.out.println("--- Ingrese Fecha de Nacimiento ---");
        // Uso de validación para la fecha de nacimiento (YYYY, MM, DD)
        int anio = GestorValidaciones.validarAnio(sc, "Año (YYYY): "); 
        int mes = GestorValidaciones.validarMes(sc, "Mes (1-12): ");
        int dia = GestorValidaciones.validarDia(sc, "Día (1-31): ");

        // Se crea el objeto LocalDate con los datos validados
        Cliente nuevo = new Cliente(nombre, apellido, telefono, email, LocalDate.of(anio, mes, dia), id, id);
        
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

    //Creación básica de una cuenta en un banco
    public void crearCuenta() {
        System.out.print("Ingrese ID del cliente: ");
        String id = sc.nextLine();
        Persona p = sistema.buscarPersona(id);
        
        if (p instanceof Cliente) {
            
            Cliente c = (Cliente) p;
            
            // Validacion de la edad al momneto de crear una cuenta
            if (!GestorValidaciones.validarEdadParaCuenta(sc, c)) {
                return; // Sale del método si no cumple la edad
            }

            // Uso de validación: Tipo de cuenta (solo letras)
            String tipo = GestorValidaciones.verificarLetras(sc, "Tipo de cuenta (solo letras): ");
            
            // Uso de validación: Saldo inicial (valor positivo > 0)
            double saldo = GestorValidaciones.validarSaldoInicial(sc);
            
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
            // Se asume la existencia de getTitularidades, getCuenta, getNumeroCuenta, getTipoCuenta, getSaldo
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
                System.out.print("Monto a depositar: ");
                double monto = GestorValidaciones.validarMonto(sc);
                System.out.print("Motivo: ");
                String motivo = sc.nextLine();
                // CAMBIO: USANDO LocalDateTime.now()
                Transaccion dep = new Deposito("T" + System.currentTimeMillis(), monto,
                        LocalDateTime.now(), empleadoActivo, cuenta, motivo);
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
                System.out.print("Monto a retirar: ");
                double monto = GestorValidaciones.validarMonto(sc);
                System.out.print("Motivo: ");
                String motivo = sc.nextLine();
                // CAMBIO: USANDO LocalDateTime.now()
                Transaccion ret = new Retiro("T" + System.currentTimeMillis(), monto,
                        LocalDateTime.now(), empleadoActivo, cuenta, motivo);
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
                // Se asume la existencia de consultarMovimientos()
                for (Transaccion tr : t.getCuenta().consultarMovimientos()) {
                    System.out.println(tr);
                }
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
}
