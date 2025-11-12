import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear el menú principal del sistema
        MenuSistema menu = new MenuSistema();
        Scanner sc = new Scanner(System.in);
        Sistema sistema = new Sistema("Banco Pichincha", "SYS001");
        
       //Iterador opcional hasta que se ingrese 0
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
            opcion = GestorValidaciones.leerOpcionMenu(sc, "Elija una opción: ");

            
            // Opciones de selección 
           switch (opcion) {
                case 1:
                    menu.verEmpleados();
                    break;
                case 2:
                    menu.agregarEmpleado();
                    break;
                case 3:
                    menu.agregarCliente();
                    break;
                case 4:
                    menu.verClientes();
                    break;
                case 5:
                    menu.crearCuenta();
                    break;
                case 6:
                    menu.verCuentasCliente();
                    break;
                case 7:
                    menu.hacerDeposito();
                    break;
                case 8:
                    menu.hacerRetiro();
                    break;
                case 9:
                    menu.verMovimientos();
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
}
