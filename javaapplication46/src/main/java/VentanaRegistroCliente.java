import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class VentanaRegistroCliente extends JDialog {

    private Sistema sistema;

    // Componentes
    private JTextField txtIdCliente, txtNombre, txtApellido, txtDni, txtCorreo, txtFechaNac, txtPin;
    private JButton btnRegistrar, btnCancelar;

    public VentanaRegistroCliente(Frame parent, Sistema sistema) {
        super(parent, "Registrar Nuevo Cliente", true);
        this.sistema = sistema;

        initComponents();
        agregarValidaciones();
        configurarVentana();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);

        // ----- Título -----
        JLabel lblTitulo = new JLabel("Registro de Nuevo Cliente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(41, 128, 185));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // ----- Panel de Formulario -----
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
                "Datos del Cliente"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== FILA 1: ID Cliente =====
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID Cliente:"), gbc);

        gbc.gridx = 1; gbc.weightx = 1;
        txtIdCliente = new JTextField(20);
        panelFormulario.add(txtIdCliente, gbc);

        // ===== FILA 2: Nombre =====
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1; gbc.weightx = 1;
        txtNombre = new JTextField(20);
        panelFormulario.add(txtNombre, gbc);

        // ===== FILA 3: Apellido =====
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Apellido:"), gbc);

        gbc.gridx = 1;
        txtApellido = new JTextField(20);
        panelFormulario.add(txtApellido, gbc);

        // ===== FILA 4: DNI =====
        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("DNI:"), gbc);

        gbc.gridx = 1;
        txtDni = new JTextField(20);
        panelFormulario.add(txtDni, gbc);

        // ===== FILA 5: Correo =====
        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(new JLabel("Correo:"), gbc);

        gbc.gridx = 1;
        txtCorreo = new JTextField(20);
        panelFormulario.add(txtCorreo, gbc);

        // ===== FILA 6: Fecha Nacimiento =====
        gbc.gridx = 0; gbc.gridy = 5;
        panelFormulario.add(new JLabel("Fecha de Nacimiento (AAAA-MM-DD):"), gbc);

        gbc.gridx = 1;
        txtFechaNac = new JTextField(20);
        panelFormulario.add(txtFechaNac, gbc);

        // ===== FILA 7: PIN =====
        gbc.gridx = 0; gbc.gridy = 6;
        panelFormulario.add(new JLabel("PIN (4 dígitos):"), gbc);

        gbc.gridx = 1;
        txtPin = new JTextField(20);
        panelFormulario.add(txtPin, gbc);

        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.WHITE);

        btnRegistrar = new JButton("Registrar Cliente");
        btnRegistrar.setPreferredSize(new Dimension(180, 35));
        btnRegistrar.setBackground(new Color(46, 204, 113));
        btnRegistrar.setForeground(new Color(41, 128, 185));
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.addActionListener(e -> registrarCliente());
        btnRegistrar.setEnabled(false); // DESHABILITADO AL INICIO

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(150, 35));
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(new Color(41, 128, 185));
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    //   VALIDACIÓN DE CAMPOS
    
    private void agregarValidaciones() {
        DocumentListener validar = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validarCampos(); }
            public void removeUpdate(DocumentEvent e) { validarCampos(); }
            public void changedUpdate(DocumentEvent e) { validarCampos(); }
        };

        txtIdCliente.getDocument().addDocumentListener(validar);
        txtNombre.getDocument().addDocumentListener(validar);
        txtApellido.getDocument().addDocumentListener(validar);
        txtDni.getDocument().addDocumentListener(validar);
        txtCorreo.getDocument().addDocumentListener(validar);
        txtFechaNac.getDocument().addDocumentListener(validar);
        txtPin.getDocument().addDocumentListener(validar);
    }

    private void validarCampos() {
        String id = txtIdCliente.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDni.getText().trim();
        String correo = txtCorreo.getText().trim();
        String fecha = txtFechaNac.getText().trim();
        String pin = txtPin.getText().trim();

        boolean ok = !id.isEmpty()
                && !nombre.isEmpty()
                && !apellido.isEmpty()
                && dni.matches("\\d{8}")
                && !correo.isEmpty()
                && pin.matches("\\d{4}")
                && esFechaValida(fecha);

        btnRegistrar.setEnabled(ok);
    }

    private boolean esFechaValida(String f) {
        try {
            LocalDate.parse(f);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //   REGISTRAR CLIENTE

    private void registrarCliente() {
        try {
            String id = txtIdCliente.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String dni = txtDni.getText().trim();
            String correo = txtCorreo.getText().trim();
            String pin = txtPin.getText().trim();
            String fechaStr = txtFechaNac.getText().trim();

            LocalDate fechaNac = LocalDate.parse(fechaStr);

            int edad = LocalDate.now().getYear() - fechaNac.getYear();
            if (edad < 18) {
                JOptionPane.showMessageDialog(this,
                        "El cliente debe ser mayor de 18 años",
                        "Menor de Edad",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (sistema.buscarPersona(id) != null) {
                JOptionPane.showMessageDialog(this,
                        "El ID ingresado ya está registrado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente nuevo = new Cliente(nombre, apellido, dni, correo, fechaNac, id, pin);
            sistema.registrarPersona(nuevo);

            JOptionPane.showMessageDialog(this,
                    "Cliente registrado exitosamente:\n\n" +
                    nuevo.getNombreCompleto() + "\nID: " + id,
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarCampos();
            btnRegistrar.setEnabled(false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al registrar cliente: " + ex.getMessage(),
                    "Error Interno",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarVentana() {
        pack();
        setSize(600, 500);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }

    private void limpiarCampos() {
        txtIdCliente.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
        txtCorreo.setText("");
        txtFechaNac.setText("");
        txtPin.setText("");
    }
}
