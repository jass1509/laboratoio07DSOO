import java.sql.*;
import java.util.ArrayList;

public class CuentaDAO {

    // Inserta una cuenta en la base de datos
    public boolean insertar(Cuenta c, String idCliente) {
        String sql = "INSERT INTO cuenta(numCuenta, idCliente, saldo, tipo) VALUES(?,?,?,?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNumeroCuenta());
            ps.setString(2, idCliente); // Se pasa desde parámetro
            ps.setDouble(3, c.getSaldo());
            ps.setString(4, c.getTipoCuenta());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERROR al crear cuenta: " + e.getMessage());
            return false;
        }
    }

    // Busca una cuenta por número
    public Cuenta buscar(String numCuenta) {
        String sql = "SELECT * FROM cuenta WHERE numCuenta=?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, numCuenta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearCuenta(rs);
            }

        } catch (SQLException e) {
            System.out.println("ERROR al buscar cuenta: " + e.getMessage());
        }
        return null;
    }

    // Actualiza el saldo de una cuenta
    public boolean actualizarSaldo(String numCuenta, double nuevoSaldo) {
        String sql = "UPDATE cuenta SET saldo=? WHERE numCuenta=?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, nuevoSaldo);
            ps.setString(2, numCuenta);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERROR al actualizar saldo: " + e.getMessage());
            return false;
        }
    }

    // Lista todas las cuentas de un cliente
    public ArrayList<Cuenta> listarPorCliente(String idCliente) {
        ArrayList<Cuenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuenta WHERE idCliente=?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearCuenta(rs));
            }

        } catch (SQLException e) {
            System.out.println("ERROR al listar cuentas: " + e.getMessage());
        }

        return lista;
    }

    // Método privado para mapear ResultSet a Cuenta
    private Cuenta mapearCuenta(ResultSet rs) throws SQLException {
        return new Cuenta(
                rs.getString("numCuenta"),
                rs.getDouble("saldo"),
                rs.getString("tipo")
        );
    }
}
