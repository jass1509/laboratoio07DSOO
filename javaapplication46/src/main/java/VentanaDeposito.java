import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VentanaDeposito extends JDialog {
    private Sistema sistema;
    private Empleado empleadoActivo;
    
    // Componentes
    private JTextField txtIdCliente, txtMonto, txtMotivo;
    private JComboBox<String> comboCuenta;
    private JButton btnBuscar, btnDepositar, btnCancelar;
    private JLabel lblClienteInfo, lblSaldoActual;
    private Cliente clienteSeleccionado;
    private Cuenta cuentaSeleccionada;
    
    public VentanaDeposito(Frame parent, Sistema sistema, Empleado empleadoActivo) {
        super(parent, "Realizar Depósito", true);
        this.sistema = sistema;
        this.empleadoActivo = empleadoActivo;
        
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
        JLabel lblTitulo = new JLabel("💰 Realizar Depósito", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(46, 204, 113));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Panel de búsqueda de cliente
        JPanel panelBusqueda = new JPanel(new GridBagLayout());
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(46, 204, 113), 2),
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
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(e -> buscarCliente());
        panelBusqueda.add(btnBuscar, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3;
        lblClienteInfo = new JLabel("No hay cliente seleccionado");
        lblClienteInfo.setForeground(Color.GRAY);
        lblClienteInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        panelBusqueda.add(lblClienteInfo, gbc);
        
        // Panel de transacción
        JPanel panelTransaccion = new JPanel(new GridBagLayout());
        panelTransaccion.setBackground(Color.WHITE);
        panelTransaccion.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(46, 204, 113), 2),
            "Datos del Depósito"));
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Selección de cuenta
        gbc.gridx = 0; gbc.gridy = 0;
        panelTransaccion.add(new JLabel("Cuenta:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        comboCuenta = new JComboBox<>();
        comboCuenta.setEnabled(false);
        comboCuenta.addActionListener(e -> actualizarSaldoActual());
        panelTransaccion.add(comboCuenta, gbc);
        
        // Saldo actual
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelTransaccion.add(new JLabel("Saldo Actual:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        lblSaldoActual = new JLabel("S/ 0.00");
        lblSaldoActual.setFont(new Font("Arial", Font.BOLD, 14));
        lblSaldoActual.setForeground(new Color(46, 204, 113));
        panelTransaccion.add(lblSaldoActual, gbc);
        
        // Monto
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelTransaccion.add(new JLabel("Monto a Depositar:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtMonto = new JTextField(20);
        txtMonto.setText("0.00");
        panelTransaccion.add(txtMonto, gbc);
        
        // Motivo
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panelTransaccion.add(new JLabel("Motivo:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtMotivo = new JTextField(20);
        panelTransaccion.add(txtMotivo, gbc);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.WHITE);
        
        btnDepositar = new JButton("Realizar Depósito");
        btnDepositar.setPreferredSize(new Dimension(170, 40));
        btnDepositar.setBackground(new Color(46, 204, 113));
        btnDepositar.setForeground(new Color(60, 60, 60));
        btnDepositar.setFont(new Font("Arial", Font.BOLD, 14));
        btnDepositar.setFocusPainted(false);
        btnDepositar.setEnabled(false);
        btnDepositar.addActionListener(e -> realizarDeposito());
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(150, 40));
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(new Color(60,60,60));
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());
        
        panelBotones.add(btnDepositar);
        panelBotones.add(btnCancelar);
        
        // Panel central
        JPanel panelCentral = new JPanel(new GridLayout(2, 1, 10, 10));
        panelCentral.setBackground(Color.WHITE);
        panelCentral.add(panelBusqueda);
        panelCentral.add(panelTransaccion);
        
        // Agregar componentes
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private void configurarVentana() {
        pack();
        setSize(600, 500);
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
            lblClienteInfo.setText("✓ Cliente: " + clienteSeleccionado.getNombreCompleto());
            lblClienteInfo.setForeground(new Color(46, 204, 113));
            
            // Cargar cuentas
            ArrayList<Titularidad> titularidades = clienteSeleccionado.getTitularidades();
            comboCuenta.removeAllItems();
            
            if (titularidades.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "El cliente no tiene cuentas registradas",
                    "Sin Cuentas",
                    JOptionPane.WARNING_MESSAGE);
                comboCuenta.setEnabled(false);
                btnDepositar.setEnabled(false);
            } else {
                for (Titularidad t : titularidades) {
                    Cuenta c = t.getCuenta();
                    comboCuenta.addItem(c.getNumeroCuenta() + " - " + c.getTipoCuenta());
                }
                comboCuenta.setEnabled(true);
                btnDepositar.setEnabled(true);
                actualizarSaldoActual();
            }
        } else {
            clienteSeleccionado = null;
            lblClienteInfo.setText("✗ Cliente no encontrado");
            lblClienteInfo.setForeground(Color.RED);
            comboCuenta.removeAllItems();
            comboCuenta.setEnabled(false);
            btnDepositar.setEnabled(false);
            JOptionPane.showMessageDialog(this,
                "No se encontró ningún cliente con ese ID",
                "Cliente No Encontrado",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void actualizarSaldoActual() {
        if (clienteSeleccionado != null && comboCuenta.getSelectedIndex() >= 0) {
            int index = comboCuenta.getSelectedIndex();
            cuentaSeleccionada = clienteSeleccionado.getTitularidades().get(index).getCuenta();
            lblSaldoActual.setText("S/ " + String.format("%.2f", cuentaSeleccionada.getSaldo()));
        }
    }
    
    private void realizarDeposito() {
        if (clienteSeleccionado == null || cuentaSeleccionada == null) {
            JOptionPane.showMessageDialog(this,
                "Primero debe buscar un cliente y seleccionar una cuenta",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double monto = Double.parseDouble(txtMonto.getText().trim());
            String motivo = txtMotivo.getText().trim();
            
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this,
                    "El monto debe ser mayor a cero",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (motivo.isEmpty()) {
                motivo = "Depósito en ventanilla";
            }
            
            // Crear transacción
            Transaccion deposito = new Deposito(
                "D" + System.currentTimeMillis(),
                monto,
                LocalDateTime.now(),
                empleadoActivo,
                cuentaSeleccionada,
                motivo
            );
            
            boolean exitoso = sistema.gestionarTransaccion(deposito);
            
            if (exitoso) {
                JOptionPane.showMessageDialog(this,
                    "Depósito realizado exitosamente\n\n" +
                    "Monto: S/ " + String.format("%.2f", monto) + "\n" +
                    "Nuevo Saldo: S/ " + String.format("%.2f", cuentaSeleccionada.getSaldo()) + "\n" +
                    "Cuenta: " + cuentaSeleccionada.getNumeroCuenta(),
                    "Depósito Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
                
                actualizarSaldoActual();
                txtMonto.setText("0.00");
                txtMotivo.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Error al procesar el depósito",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "El monto debe ser un número válido",
                "Error de Formato",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al realizar el depósito: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
