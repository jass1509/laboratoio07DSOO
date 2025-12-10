import java.sql.*;
import java.util.ArrayList;

public class TransaccionDAO {

    // Registrar una transacción
    public boolean registrar(Transaccion t) {
        String sql = "INSERT INTO transaccion(idTrans, numCuenta, tipo, monto, fecha, idEmpleado, motivo) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getCodigo());
            ps.setString(2, t.getCuentaRegistra().getNumeroCuenta());
            ps.setString(3, t.getClass().getSimpleName()); // tipo de transacción (nombre de clase)
            ps.setDouble(4, t.getMonto());
            ps.setTimestamp(5, Timestamp.valueOf(t.getFecha()));
            ps.setString(6, t.getEmpleadoRegistra().getCodigoEmpleado());
            ps.setString(7, t.getMotivo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERROR al registrar transacción: " + e.getMessage());
            return false;
        }
    }

    // Listar transacciones de una cuenta (devuelve info básica, no instancias concretas)
    public ArrayList<String> listar(String numCuenta) {
        ArrayList<String> lista = new ArrayList<>();
        String sql = "SELECT * FROM transaccion WHERE numCuenta=?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, numCuenta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String info = "ID: " + rs.getString("idTrans") +
                              " | Tipo: " + rs.getString("tipo") +
                              " | Monto: " + rs.getDouble("monto") +
                              " | Fecha: " + rs.getTimestamp("fecha").toLocalDateTime() +
                              " | Empleado: " + rs.getString("idEmpleado") +
                              " | Motivo: " + rs.getString("motivo");
                lista.add(info);
            }

        } catch (SQLException e) {
            System.out.println("ERROR al listar transacciones: " + e.getMessage());
        }

        return lista;
    }
}
