import java.time.LocalDate;
import java.util.Scanner;

public class GestorValidaciones {

    // Constante para la edad mínima de apertura de cuenta
    private static final int EDAD_MINIMA_CUENTA = 18;

    /* Valida que una cadena contenga solo letras y espacios. */
    public static String verificarLetras(Scanner sc, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input != null && input.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$") && !input.isEmpty()) {
                return input;
            } else {
                System.out.println("Error: Solo se aceptan letras y espacios. Vuelva a ingresar.");
            }
        }
    }

    /* Valida que la entrada sea un número de teléfono de 9 dígitos. */
    public static String verificarNumeros(Scanner sc, String prompt) {
        String numero;
        while (true) {
            System.out.print(prompt);
            numero = sc.nextLine().trim();
            if (numero != null && numero.matches("^\\d{9}$")) {
                return numero;
            } else {
                System.out.println("Error: Solo se acepta un número de 9 dígitos. Vuelva a ingresar.");
            }
        }
    }
    
    /* Valida el formato de un correo electrónico. */
    public static String verificarCorreo(Scanner sc, String prompt) {
        String correo;
        while (true) {
            System.out.print(prompt);
            correo = sc.nextLine().trim();
            if (correo != null && correo.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                return correo;
            } else {
                System.out.println("Error: Correo inválido. Vuelva a ingresar.");
            }
        }
    }

    /* Valida que la entrada sea un número (monto) positivo. */
    public static double validarMonto(Scanner sc) {
        while (true) {
            try {
                double monto = Double.parseDouble(sc.nextLine());
                if (monto > 0) {
                    return monto;
                } else {
                    System.out.print("Error: El monto debe ser un valor positivo. Ingrese nuevamente: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Error: Ingrese un valor numérico válido: ");
            }
        }
    }
    
    /* Valida el saldo inicial, asegurando que sea un valor positivo. */
    public static double validarSaldoInicial(Scanner sc) {
          System.out.print("Saldo inicial (mínimo S/0.01): ");
          while (true) {
            try {
                double saldo = Double.parseDouble(sc.nextLine());
                if (saldo > 0) {
                    return saldo;
                } else {
                    System.out.print("Error: El saldo inicial debe ser mayor a cero. Ingrese nuevamente: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Error: Ingrese un valor numérico válido: ");
            }
        }
    }

    /* Valida que la entrada sea un número entero y positivo. */
    private static int validarEntero(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int valor = Integer.parseInt(sc.nextLine());
                if (valor > 0) {
                    return valor;
                } else {
                    System.out.println("Error: El valor debe ser un número entero positivo. Vuelva a ingresar.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico entero válido. Vuelva a ingresar.");
            }
        }
    }

    /* Valida el año de nacimiento (entre 1900 y el año actual). */
    public static int validarAnio(Scanner sc, String prompt) {
        int anioActual = LocalDate.now().getYear();
        int anio;
        while (true) {
            anio = validarEntero(sc, prompt);
            if (anio >= 1900 && anio <= anioActual) {
                return anio;
            } else {
                System.out.println("Error: El año debe estar entre 1900 y " + anioActual + ". Vuelva a ingresar.");
            }
        }
    }

    /* Valida el mes (entre 1 y 12). */
    public static int validarMes(Scanner sc, String prompt) {
        int mes;
        while (true) {
            mes = validarEntero(sc, prompt);
            if (mes >= 1 && mes <= 12) {
                return mes;
            } else {
                System.out.println("Error: El mes debe estar entre 1 y 12. Vuelva a ingresar.");
            }
        }
    }

    /* Valida el día (entre 1 y 31, con verificación básica de meses). */
    public static int validarDia(Scanner sc, String prompt) {
        int dia;
        while (true) {
            dia = validarEntero(sc, prompt);
            if (dia >= 1 && dia <= 31) {
                if ((dia == 31) && (prompt.toLowerCase().contains("abril") || prompt.toLowerCase().contains("junio") || 
                                   prompt.toLowerCase().contains("septiembre") || prompt.toLowerCase().contains("noviembre"))) {
                    System.out.println("Error: Este mes no tiene 31 días. Vuelva a ingresar.");
                } else if ((dia > 29) && prompt.toLowerCase().contains("febrero")) {
                    System.out.println("Error: Febrero tiene un máximo de 29 días. Vuelva a ingresar.");
                } else {
                    return dia;
                }
            } else {
                System.out.println("Error: El día debe estar entre 1 y 31. Vuelva a ingresar.");
            }
        }
    }
    
    /* Valida si el cliente es mayor de edad (18 años) para la apertura de cuenta. */
    public static boolean validarEdadParaCuenta(Scanner sc, Persona persona) {
        int edad = persona.calcularEdad();
        if (edad < EDAD_MINIMA_CUENTA) {
            System.out.println("\n ERROR: El cliente debe tener al menos " + EDAD_MINIMA_CUENTA + " años para abrir una cuenta.");
            System.out.println("Edad actual del cliente: " + edad + " años.");
            return false;
        }
        return true;
    }
    
    /* Lee una opción del menú principal (1-11). */
    public static int leerOpcionMenu(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int valor = Integer.parseInt(sc.nextLine());
                if (valor >= 1 && valor <= 11) { 
                    return valor;
                } else {
                    System.out.println("Error: El valor debe ser un número entre 1 y 11.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico entero válido.");
            }
        }
    }

    /* MÉTODO CORREGIDO: Valida que la entrada sea un número double válido y positivo. */
    public static double verificarDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double valor = Double.parseDouble(sc.nextLine());
                if (valor > 0) {
                    return valor;
                } else {
                    System.out.println("Error: El valor debe ser un número positivo. Vuelva a ingresar.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido. Vuelva a ingresar.");
            }
        }
    }
}
