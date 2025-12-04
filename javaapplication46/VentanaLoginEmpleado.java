import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaLoginEmpleado extends JDialog {

    private JTextField txtId;
    private JPasswordField txtPin;
    private JButton btnIngresar, btnCancelar;

    private Sistema sistema;
    private Empleado empleadoAutenticado = null;

    public VentanaLoginEmpleado(Frame parent, Sistema sistema) {
        super(parent, "Validación de Empleado", true);
        this.sistema = sistema;

        initComponents();
        configurarVentana();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblId = new JLabel("ID Empleado:");
        JLabel lblPin = new JLabel("PIN:");

        txtId = new JTextField(12);
        txtPin = new JPasswordField(12);

        btnIngresar = new JButton("Ingresar");
        btnCancelar = new JButton("Cancelar");

        gbc.gridx = 0; gbc.gridy = 0; add(lblId, gbc);
        gbc.gridx = 1; add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(lblPin, gbc);
        gbc.gridx = 1; add(txtPin, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(btnCancelar, gbc);
        gbc.gridx = 1; add(btnIngresar, gbc);

        btnIngresar.addActionListener(e -> validar());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void configurarVentana() {
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(getParent());
    }

    private void validar() {
        String id = txtId.getText().trim();
        String pin = new String(txtPin.getPassword()).trim();

        Persona persona = sistema.buscarPersona(id);

        if (persona instanceof Empleado emp) {
            if (emp.getCodigoEmpleado().equals(id) && pin.equals("1234")) { // CAMBIA si tienes PIN real
                empleadoAutenticado = emp;
                dispose();
                return;
            }
        }

        JOptionPane.showMessageDialog(this,
                "Datos incorrectos. Acceso denegado.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public Empleado getEmpleadoAutenticado() {
        return empleadoAutenticado;
    }
}
