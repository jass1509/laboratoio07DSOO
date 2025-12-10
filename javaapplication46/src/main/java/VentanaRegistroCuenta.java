import javax.swing.*;
import java.awt.*;

public class VentanaRegistroCuenta extends JDialog {
    private Sistema sistema;
    
    // Componentes
    private JTextField txtIdCliente, txtSaldoInicial;
    private JComboBox<String> comboTipoCuenta;
    private JButton btnBuscar, btnCrear, btnCancelar;
    private JLabel lblClienteEncontrado;
    private Cliente clienteSeleccionado;
    
    
    public VentanaRegistroCuenta(Frame parent, Sistema sistema) {
        super(parent, "Registrar Nueva Cuenta", true);
        this.sistema = sistema;
        
        initComponents();
        configurarVentana();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);
        
        // Título
        JLabel lblTitulo = new JLabel("Crear Nueva Cuenta Bancaria", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(41, 128, 185));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Panel de búsqueda de cliente
        JPanel panelBusqueda = new JPanel(new GridBagLayout());
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
            "Buscar Cliente"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panelBusqueda.add(new JLabel("ID Cliente:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtIdCliente = new JTextField(20);
        panelBusqueda.add(txtIdCliente, gbc);
        
        gbc.gridx = 2; gbc.weightx = 0;
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(52, 152, 219));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(e -> buscarCliente());
        panelBusqueda.add(btnBuscar, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3;
        lblClienteEncontrado = new JLabel("No hay cliente seleccionado");
        lblClienteEncontrado.setForeground(Color.GRAY);
        lblClienteEncontrado.setFont(new Font("Arial", Font.ITALIC, 12));
        panelBusqueda.add(lblClienteEncontrado, gbc);
        
        // Panel de datos de cuenta
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
            "Datos de la Cuenta"));
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Tipo de cuenta
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Tipo de Cuenta:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        String[] tiposCuenta = {"Ahorros", "Corriente", "Plazo Fijo", "CTS"};
        comboTipoCuenta = new JComboBox<>(tiposCuenta);
        panelFormulario.add(comboTipoCuenta, gbc);
        
        // Saldo inicial
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Saldo Inicial:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtSaldoInicial = new JTextField(20);
        txtSaldoInicial.setText("0.00");
        panelFormulario.add(txtSaldoInicial, gbc);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.WHITE);
        
        btnCrear = new JButton("Crear Cuenta");
        btnCrear.setPreferredSize(new Dimension(150, 35));
        btnCrear.setBackground(new Color(46, 204, 113));
        btnCrear.setForeground(new Color(60, 60, 60));
        btnCrear.setFont(new Font("Arial", Font.BOLD, 14));
        btnCrear.setFocusPainted(false);
        btnCrear.setEnabled(false);
        btnCrear.addActionListener(e -> crearCuenta());
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(150, 35));
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(new Color(60,60,60));
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());
        
        panelBotones.add(btnCrear);
        panelBotones.add(btnCancelar);
        
        // Panel central
        JPanel panelCentral = new JPanel(new GridLayout(2, 1, 10, 10));
        panelCentral.setBackground(Color.WHITE);
        panelCentral.add(panelBusqueda);
        panelCentral.add(panelFormulario);
        
        // Agregar componentes
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private void configurarVentana() {
        pack();
        setSize(550, 400);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }
    
    private void buscarCliente() {
        String idCliente = txtIdCliente.getText().trim();
        
        if (idCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Ingrese un ID de cliente",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Persona p = sistema.buscarPersona(idCliente);
        
        if (p instanceof Cliente) {
            clienteSeleccionado = (Cliente) p;
            lblClienteEncontrado.setText("✓ Cliente: " + clienteSeleccionado.getNombreCompleto() + 
                                        " (Edad: " + clienteSeleccionado.calcularEdad() + " años)");
            lblClienteEncontrado.setForeground(new Color(46, 204, 113));
            
            // Validar edad
            if (clienteSeleccionado.calcularEdad() < 18) {
                JOptionPane.showMessageDialog(this,
                    "El cliente debe tener al menos 18 años para abrir una cuenta",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
                clienteSeleccionado = null;
                lblClienteEncontrado.setText("Cliente menor de edad - No puede abrir cuenta");
                lblClienteEncontrado.setForeground(Color.RED);
                btnCrear.setEnabled(false);
            } else {
                btnCrear.setEnabled(true);
            }
        } else {
            clienteSeleccionado = null;
            lblClienteEncontrado.setText("✗ Cliente no encontrado");
            lblClienteEncontrado.setForeground(Color.RED);
            btnCrear.setEnabled(false);
            JOptionPane.showMessageDialog(this,
                "No se encontró ningún cliente con ese ID",
                "Cliente No Encontrado",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void crearCuenta() {
        if (clienteSeleccionado == null) {
            JOptionPane.showMessageDialog(this,
                "Primero debe buscar y seleccionar un cliente",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String tipoCuenta = (String) comboTipoCuenta.getSelectedItem();
            double saldoInicial = Double.parseDouble(txtSaldoInicial.getText().trim());
            
            if (saldoInicial < 0) {
                JOptionPane.showMessageDialog(this,
                    "El saldo inicial no puede ser negativo",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear cuenta
            Cuenta nuevaCuenta = sistema.crearCuenta(clienteSeleccionado, tipoCuenta, saldoInicial);
            
            JOptionPane.showMessageDialog(this,
                "Cuenta creada exitosamente\n\n" +
                "Número de Cuenta: " + nuevaCuenta.getNumeroCuenta() + "\n" +
                "Tipo: " + nuevaCuenta.getTipoCuenta() + "\n" +
                "Saldo Inicial: S/ " + String.format("%.2f", nuevaCuenta.getSaldo()) + "\n" +
                "Titular: " + clienteSeleccionado.getNombreCompleto(),
                "Cuenta Creada",
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCampos();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "El saldo inicial debe ser un número válido",
                "Error de Formato",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al crear la cuenta: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtIdCliente.setText("");
        txtSaldoInicial.setText("0.00");
        comboTipoCuenta.setSelectedIndex(0);
        lblClienteEncontrado.setText("No hay cliente seleccionado");
        lblClienteEncontrado.setForeground(Color.GRAY);
        clienteSeleccionado = null;
        btnCrear.setEnabled(false);
    }
}
