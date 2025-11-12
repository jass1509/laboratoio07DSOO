package javaapplication46;
import java.util.*;
public class GestorValidaciones {

    public String verificarLetras (String palabra) {
        if (palabra != null && palabra.matches("^[a-zA-Z\\s]+$")){
            return "";
        } else {
            return "Solo se aceptan letras, vuelva a ingresar los datos";
        }
    }
    public String verificarNúmeros (String numero) {

        if (numero != null && numero.matches("^\\d{9}$")){
            return "";
        } else {
            return "Solo se acepta 9 digitos vuelva a ingresar los datos";
        }      
    }
    public static String verificarCorreo (String correo) {
        if (correo != null && correo.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")){
            return "";
        } else {
            return "Correo invalido, vuelva a ingresar los datos";
        }      
    }
    public static double validarMonto(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un valor numérico válido: ");
            }
        }
    }
}
