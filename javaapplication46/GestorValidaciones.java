public class Validaciones {

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

}
