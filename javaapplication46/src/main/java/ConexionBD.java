import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/banco?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // cambia si usas contraseña

    public static Connection conectar() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a MySQL!");
            return con;
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
