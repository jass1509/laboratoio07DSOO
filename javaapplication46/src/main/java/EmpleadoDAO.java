import java.sql.*;
import java.util.ArrayList;

public class EmpleadoDAO {

    // Inserta un empleado en la base de datos
    public boolean insertar(Empleado e) {
        String sql = "INSERT INTO empleado(idEmpleado, nombre, apellido, telefono, email, fechaNacimiento, fechaContratacion, salario, departamento, pin) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getCodigoEmpleado());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setString(4, e.getTelefono());
            ps.setString(5, e.getEmail());
            ps.setDate(6, Date.valueOf(e.getFechaNacimiento()));
            ps.setDate(7, Date.valueOf(e.getFechaContratacion()));
            ps.setDouble(8, e.getSalario());
            ps.setString(9, e.getDepartamento());
            ps.setString(10, e.getPin());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("ERROR al insertar empleado: " + ex.getMessage());
            return false;
        }
    }

    // Busca un empleado por código
    public Empleado buscar(String idEmpleado) {
        String sql = "SELECT * FROM empleado WHERE idEmpleado=?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idEmpleado);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearEmpleado(rs);
            }

        } catch (SQLException e) {
            System.out.println("ERROR al buscar empleado: " + e.getMessage());
        }
        return null;
    }

    // Lista todos los empleados
    public ArrayList<Empleado> listar() {
        ArrayList<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (Connection conn = ConexionBD.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearEmpleado(rs));
            }

        } catch (SQLException e) {
            System.out.println("ERROR al listar empleados: " + e.getMessage());
        }

        return lista;
    }

    // Método privado para mapear ResultSet a Empleado
    private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getDate("fechaNacimiento").toLocalDate(),
                rs.getString("idEmpleado"),
                rs.getDate("fechaContratacion").toLocalDate(),
                rs.getDouble("salario"),
                rs.getString("departamento"),
                rs.getString("pin")
        );
    }
}
