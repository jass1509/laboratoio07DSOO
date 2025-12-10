import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    // Inserta un cliente en la base de datos
    public boolean insertar(Cliente c) {
        String sql = "INSERT INTO cliente(idCliente, nombre, apellido, telefono, email, fechaNacimiento, pin) VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getIdCliente());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getEmail());
            ps.setDate(6, Date.valueOf(c.getFechaNacimiento())); // LocalDate a java.sql.Date
            ps.setString(7, c.getPin());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERROR al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    // Busca un cliente por id
    public Cliente buscar(String idCliente) {
        String sql = "SELECT * FROM cliente WHERE idCliente=?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearCliente(rs);
            }

        } catch (SQLException e) {
            System.out.println("ERROR al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    // Lista todos los clientes
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = ConexionBD.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearCliente(rs));
            }

        } catch (SQLException e) {
            System.out.println("ERROR al listar clientes: " + e.getMessage());
        }

        return lista;
    }

    // Método privado para mapear ResultSet a Cliente
    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getDate("fechaNacimiento").toLocalDate(),
                rs.getString("idCliente"),
                rs.getString("pin")
        );
    }
}
