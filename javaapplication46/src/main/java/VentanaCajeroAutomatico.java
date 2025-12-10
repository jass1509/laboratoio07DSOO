import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VentanaCajeroAutomatico extends JDialog {
    private Sistema sistema;
    private Empleado empleadoATM;
    
    // Componentes
    private JTextField txtIdCliente;
    private JPasswordField txtPin;
    private JComboBox<String> comboCuenta;
    private JButton btnAutenticar, btnConsultar, btnRetirar, btnDepositar, btnSalir;
    private JLabel lblEstado, lblSaldo;
    private JPanel panelOperaciones;
    private Cliente clienteAutenticado;
    private Cuenta cuentaSeleccionada;
    
    public VentanaCajeroAutomatico(Frame parent, Sistema sistema, Empleado empleadoATM) {
        super(parent, "Cajero Automático", true);
        this.sistema = sistema;
        this.empleadoATM = empleadoATM;
        
        initComponents();
        configurarVentana();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel principal con color de ATM
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(44, 62, 80));
        
        // Título
        JLabel lblTitulo = new JLabel("🏧 CAJERO AUTOMÁTICO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Panel de autenticación
        JPanel panelAuth = new JPanel(new GridBagLayout());
        panelAuth.setBackground(Color.WHITE);
        panelAuth.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
            "Iniciar Sesión"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panelAuth.add(new JLabel("ID Cliente:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtIdCliente = new JTextField(15);
        panelAuth.add(txtIdCliente, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelAuth.add(new JLabel("PIN:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtPin = new JPasswordField(15);
        panelAuth.add(txtPin, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        btnAutenticar = new JButton("Iniciar Sesión");
        btnAutenticar.setBackground(new Color(46, 204, 113));
        btnAutenticar.setForeground(Color.BLACK);
        btnAutenticar.setFocusPainted(false);
        btnAutenticar.setPreferredSize(new Dimension(200, 35));
        btnAutenticar.addActionListener(e -> autenticar());
        panelAuth.add(btnAutenticar, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        lblEstado = new JLabel("Por favor, ingrese sus credenciales");
        lblEstado.setForeground(Color.GRAY);
        lblEstado.setFont(new Font("Arial", Font.ITALIC, 11));
        panelAuth.add(lblEstado, gbc);
        
        // Panel de selección de cuenta
        JPanel panelCuenta = new JPanel(new GridBagLayout());
        panelCuenta.setBackground(Color.WHITE);
        panelCuenta.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
            "Seleccionar Cuenta"));
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panelCuenta.add(new JLabel("Cuenta:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        comboCuenta = new JComboBox<>();
        comboCuenta.setEnabled(false);
        comboCuenta.addActionListener(e -> seleccionarCuenta());
        panelCuenta.add(comboCuenta, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panelCuenta.add(new JLabel("Saldo:"), gbc);
        
        gbc.gridx = 1;
        lblSaldo = new JLabel("S/ 0.00");
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        lblSaldo.setForeground(new Color(46, 204, 113));
        panelCuenta.add(lblSaldo, gbc);
        
        // Panel de operaciones
        panelOperaciones = new JPanel(new GridLayout(2, 2, 10, 10));
        panelOperaciones.setBackground(Color.WHITE);
        panelOperaciones.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
            "Operaciones"));
        
        btnConsultar = crearBotonOperacion("Consultar Saldo", new Color(52, 152, 219));
        btnConsultar.addActionListener(e -> consultarSaldo());
        
        btnRetirar = crearBotonOperacion("Retirar", new Color(231, 76, 60));
        btnRetirar.addActionListener(e -> retirar());
        
        btnDepositar = crearBotonOperacion("Depositar", new Color(46, 204, 113));
        btnDepositar.addActionListener(e -> depositar());
        
        btnSalir = crearBotonOperacion("Cerrar Sesión", new Color(149, 165, 166));
        btnSalir.addActionListener(e -> dispose());
        
        panelOperaciones.add(btnConsultar);
        panelOperaciones.add(btnRetirar);
        panelOperaciones.add(btnDepositar);
        panelOperaciones.add(btnSalir);
        
        deshabilitarOperaciones();
        
        // Panel central
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBackground(Color.WHITE);
        panelCentral.add(panelAuth, BorderLayout.NORTH);
        panelCentral.add(panelCuenta, BorderLayout.CENTER);
        panelCentral.add(panelOperaciones, BorderLayout.SOUTH);
        
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        
        add(panelPrincipal);
    }
    
    private JButton crearBotonOperacion(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(new Color(60, 60, 60));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(150, 50));
        return btn;
    }
    
    private void configurarVentana() {
        pack();
        setSize(500, 600);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }
    
    private void autenticar() {
        String idCliente = txtIdCliente.getText().trim();
        String pin = new String(txtPin.getPassword());
        
        if (idCliente.isEmpty() || pin.isEmpty()) {
            lblEstado.setText("❌ Complete todos los campos");
            lblEstado.setForeground(Color.RED);
            return;
        }
        
        Persona p = sistema.buscarPersona(idCliente);
        
        if (p instanceof Cliente) {
            Cliente cliente = (Cliente) p;
            
            if (cliente.getPin().equals(pin)) {
                clienteAutenticado = cliente;
                lblEstado.setText("✓ Bienvenido, " + cliente.getNombreCompleto());
                lblEstado.setForeground(new Color(46, 204, 113));
                
                // Cargar cuentas
                ArrayList<Titularidad> titularidades = cliente.getTitularidades();
                comboCuenta.removeAllItems();
                
                if (titularidades.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "No tiene cuentas asociadas",
                        "Sin Cuentas",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                for (Titularidad t : titularidades) {
                    Cuenta c = t.getCuenta();
                    comboCuenta.addItem(c.getNumeroCuenta() + " - " + c.getTipoCuenta());
                }
                
                comboCuenta.setEnabled(true);
                habilitarOperaciones();
                seleccionarCuenta();
                
                // Deshabilitar autenticación
                txtIdCliente.setEnabled(false);
                txtPin.setEnabled(false);
                btnAutenticar.setEnabled(false);
                
            } else {
                lblEstado.setText("❌ PIN incorrecto");
                lblEstado.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this,
                    "PIN incorrecto",
                    "Error de Autenticación",
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            lblEstado.setText("❌ Cliente no encontrado");
            lblEstado.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this,
                "Cliente no encontrado",
                "Error de Autenticación",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void seleccionarCuenta() {
        if (clienteAutenticado != null && comboCuenta.getSelectedIndex() >= 0) {
            int index = comboCuenta.getSelectedIndex();
            cuentaSeleccionada = clienteAutenticado.getTitularidades().get(index).getCuenta();
            lblSaldo.setText("S/ " + String.format("%.2f", cuentaSeleccionada.getSaldo()));
        }
    }
    
    private void consultarSaldo() {
        if (cuentaSeleccionada != null) {
            JOptionPane.showMessageDialog(this,
                "Cuenta: " + cuentaSeleccionada.getNumeroCuenta() + "\n" +
                "Tipo: " + cuentaSeleccionada.getTipoCuenta() + "\n" +
                "Saldo Actual: S/ " + String.format("%.2f", cuentaSeleccionada.getSaldo()),
                "Consulta de Saldo",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void retirar() {
        if (cuentaSeleccionada == null) return;
        
        String montoStr = JOptionPane.showInputDialog(this,
            "Ingrese el monto a retirar:\n(Límite: S/ 1,000.00 | Múltiplos de 10)",
            "Retiro");
        
        if (montoStr == null || montoStr.trim().isEmpty()) return;
        
        try {
            double monto = Double.parseDouble(montoStr);
            
            if (monto <= 0 || monto % 10 != 0) {
                JOptionPane.showMessageDialog(this,
                    "El monto debe ser positivo y múltiplo de 10",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Transaccion retiro = new Retiro("ATM" + System.currentTimeMillis(), monto,
                LocalDateTime.now(), empleadoATM, cuentaSeleccionada, "Retiro en Cajero Automático");
            
            if (sistema.gestionarTransaccion(retiro)) {
                lblSaldo.setText("S/ " + String.format("%.2f", cuentaSeleccionada.getSaldo()));
                JOptionPane.showMessageDialog(this,
                    "Retiro exitoso\nNuevo saldo: S/ " + String.format("%.2f", cuentaSeleccionada.getSaldo()),
                    "Transacción Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "No se pudo realizar el retiro\nVerifique su saldo o el límite diario",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Monto inválido",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void depositar() {
        if (cuentaSeleccionada == null) return;
        
        String montoStr = JOptionPane.showInputDialog(this,
            "Ingrese el monto a depositar:",
            "Depósito");
        
        if (montoStr == null || montoStr.trim().isEmpty()) return;
        
        try {
            double monto = Double.parseDouble(montoStr);
            
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this,
                    "El monto debe ser positivo",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Transaccion deposito = new Deposito("ATM" + System.currentTimeMillis(), monto,
                LocalDateTime.now(), empleadoATM, cuentaSeleccionada, "Depósito en Cajero Automático");
            
            if (sistema.gestionarTransaccion(deposito)) {
                lblSaldo.setText("S/ " + String.format("%.2f", cuentaSeleccionada.getSaldo()));
                JOptionPane.showMessageDialog(this,
                    "Depósito exitoso\nNuevo saldo: S/ " + String.format("%.2f", cuentaSeleccionada.getSaldo()),
                    "Transacción Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Error al procesar el depósito",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Monto inválido",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void habilitarOperaciones() {
        btnConsultar.setEnabled(true);
        btnRetirar.setEnabled(true);
        btnDepositar.setEnabled(true);
    }
    
    private void deshabilitarOperaciones() {
        btnConsultar.setEnabled(false);
        btnRetirar.setEnabled(false);
        btnDepositar.setEnabled(false);
    }
}
