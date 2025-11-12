import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class CajeroAutomatico {
    private Sistema sistema;
    private Scanner sc;
    private Empleado empleadoATM; // El 'Empleado' que registrará las transacciones del cajero.

    // El Cajero necesita una referencia al Sistema, el Scanner y el Empleado especial.
    public CajeroAutomatico(Sistema sistema, Empleado empleadoATM, Scanner sc) {
        this.sistema = sistema;
        this.empleadoATM = empleadoATM;
        this.sc = sc;
    }

    // Método principal para iniciar la sesión del cajero
    public void iniciarCajero() {
        System.out.println("\n===== BIENVENIDO AL CAJERO AUTOMÁTICO =====");
        
        // --- 1. Autenticación ---
        Cliente clienteLogueado = null;
        Cuenta cuentaSeleccionada = null;

        System.out.print("Ingrese su ID de Cliente: ");
        String idCliente = sc.nextLine().trim();
        
        Persona p = sistema.buscarPersona(idCliente);
        
        if (p instanceof Cliente) {
            clienteLogueado = (Cliente) p;

            System.out.print("Ingrese su PIN: "); 
            String pinIngresado = sc.nextLine().trim();

            // Usando el nuevo getPin() de Cliente (asumiendo la modificación anterior)
            if (!clienteLogueado.getPin().equals(pinIngresado)) { 
                 System.out.println("ERROR: ID o PIN incorrecto. Saliendo del Cajero.");
                 return;
            }
            
            System.out.println("Autenticación exitosa. Hola, " + clienteLogueado.getNombreCompleto());
            
            // --- 2. Selección de Cuenta (Si el cliente tiene más de una) ---
            ArrayList<Titularidad> titularidades = clienteLogueado.getTitularidades();
            if (titularidades.isEmpty()) {
                System.out.println("ERROR: No tiene cuentas asociadas. Saliendo del Cajero.");
                return;
            }
            
            if (titularidades.size() == 1) {
                cuentaSeleccionada = titularidades.get(0).getCuenta();
                System.out.println("Cuenta seleccionada automáticamente: " + cuentaSeleccionada.getNumeroCuenta());
            } else {
                System.out.println("\nCuentas disponibles:");
                for (int i = 0; i < titularidades.size(); i++) {
                    Cuenta c = titularidades.get(i).getCuenta();
                    System.out.println((i + 1) + ". Cuenta " + c.getNumeroCuenta() + " (" + c.getTipoCuenta() + ")");
                }
                
                int opcionCuenta = GestorValidaciones.leerOpcionMenu(sc, "Seleccione la cuenta (1-" + titularidades.size() + "): ");
                
                if (opcionCuenta > 0 && opcionCuenta <= titularidades.size()) {
                    cuentaSeleccionada = titularidades.get(opcionCuenta - 1).getCuenta();
                    System.out.println("Ha seleccionado la cuenta: " + cuentaSeleccionada.getNumeroCuenta());
                } else {
                    System.out.println("Opción de cuenta no válida. Saliendo del Cajero.");
                    return;
                }
            }

        } else {
            System.out.println("ERROR: Cliente no encontrado con ID: " + idCliente + ". Saliendo del Cajero.");
            return;
        }

        // --- 3. Menú de Transacciones ---
        int opcionATM;
        do {
            System.out.println("\n===== MENÚ DE OPERACIONES - CUENTA: " + cuentaSeleccionada.getNumeroCuenta() + " =====");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Retiro de Efectivo");
            System.out.println("3. Depósito de Efectivo");
            System.out.println("4. Ver Últimos Movimientos");
            System.out.println("5. Salir del Cajero");
            
            // Reutilizamos GestorValidaciones, pero limitamos la entrada a 1-5.
            opcionATM = leerOpcionATM(sc, "Elija una opción (1-5): "); 

            switch (opcionATM) {
                case 1:
                    consultarSaldo(cuentaSeleccionada);
                    break;
                case 2:
                    hacerRetiro(cuentaSeleccionada, empleadoATM);
                    break;
                case 3:
                    hacerDeposito(cuentaSeleccionada, empleadoATM);
                    break;
                case 4:
                    verMovimientos(cuentaSeleccionada);
                    break;
                case 5:
                    System.out.println("Gracias por usar el Cajero Automático. Sesión terminada.");
                    break;
            }

        } while (opcionATM != 5);
    }
    
    // Método auxiliar para leer opciones en el Cajero (rango 1-5)
    private int leerOpcionATM(Scanner sc, String prompt) {
         while (true) {
            System.out.print(prompt);
            try {
                int valor = Integer.parseInt(sc.nextLine());
                if (valor >= 1 && valor <= 5) { 
                    return valor;
                } else {
                    System.out.println("Error: El valor debe ser un número entre 1 y 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico entero válido.");
            }
        }
    }

    private void consultarSaldo(Cuenta c) {
        System.out.printf("\nSALDO ACTUAL: $%.2f\n", c.getSaldo());
    }

    private void hacerRetiro(Cuenta cuenta, Empleado atm) {
        double monto = GestorValidaciones.verificarDouble(sc, "Ingrese monto a retirar: ");
        
        if (monto <= 0) {
            System.out.println("Error: El monto debe ser positivo.");
            return;
        }
        
        // Regla de ATM: solo múltiplos de 10
        if (monto % 10 != 0) { 
            System.out.println("ADVERTENCIA: El cajero solo dispensa múltiplos de 10. Intente de nuevo.");
            return;
        }

        // Se crea el Retiro usando el Empleado ATM
        Transaccion ret = new Retiro("A" + System.currentTimeMillis(), monto, 
                LocalDateTime.now(), atm, cuenta, "Retiro en Cajero Automático");
        
        boolean ok = sistema.gestionarTransaccion(ret);

        if (ok) {
            System.out.println("Retiro exitoso. Su nuevo saldo es: $" + cuenta.getSaldo());
        } else {
            // Retiro.java tiene una restricción de límite (1000) y saldo.
            System.out.println("No se pudo realizar el retiro. Verifique su saldo, el límite diario (1000) o el monto ingresado.");
        }
    }

    private void hacerDeposito(Cuenta cuenta, Empleado atm) {
        double monto = GestorValidaciones.verificarDouble(sc, "Ingrese monto a depositar: ");
        
        if (monto <= 0) {
            System.out.println("Error: El monto debe ser positivo.");
            return;
        }
        
        // Se crea el Depósito usando el Empleado ATM
        Transaccion dep = new Deposito("A" + System.currentTimeMillis(), monto, 
                LocalDateTime.now(), atm, cuenta, "Depósito en Cajero Automático");
        
        boolean ok = sistema.gestionarTransaccion(dep);
        
        if (ok) {
             System.out.println("Depósito exitoso. Su nuevo saldo es: $" + cuenta.getSaldo());
        } else {
             // Esto solo pasaría por un error en la lógica de Sistema
             System.out.println("Error inesperado al procesar el depósito.");
        }
    }
    
    private void verMovimientos(Cuenta c) {
         System.out.println("\n--- Últimos 5 Movimientos de la Cuenta " + c.getNumeroCuenta() + " ---");
         ArrayList<Transaccion> movimientos = c.consultarMovimientos();
         int totalMovimientos = movimientos.size();
         int inicio = Math.max(0, totalMovimientos - 5); // Mostrar solo los últimos 5
         int contador = 0;
         
         if (totalMovimientos == 0) {
             System.out.println("No hay movimientos registrados en esta cuenta.");
             return;
         }
         
         // Muestra de más reciente a más antiguo
         for (int i = totalMovimientos - 1; i >= inicio; i--) { 
             Transaccion tr = movimientos.get(i);
             System.out.println(tr);
             contador++;
         }
         System.out.println("--- Se mostraron " + contador + " movimientos de un total de " + totalMovimientos + " ---");
    }
}