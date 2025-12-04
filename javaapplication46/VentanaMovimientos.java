import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaMovimientos extends JDialog {
    private Sistema sistema;
    
    // Componentes
    private JTextField txtIdCliente;
    private JComboBox<String> comboCuenta;
    private JButton btnBuscar, btnVerMovimientos, btnCerrar;
    private JLabel lblClienteInfo;
    private JTable tablaMovimientos;
    private DefaultTableModel modeloTabla;
    private Cliente clienteSeleccionado;
    
    public VentanaMovimientos(Frame parent, Sistema sistema) {
        super(parent, "Ver Movimientos", true);
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
        JLabel lblTitulo = new JLabel("📋 Historial de Movimientos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(155, 89, 182));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new GridBagLayout());
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(155, 89, 182), 2),
            "Seleccionar Cliente y Cuenta"));
        
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
        btnBuscar.setBackground(new Color(155, 89, 182));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(e -> buscarCliente());
        panelBusqueda.add(btnBuscar, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panelBusqueda.add(new JLabel("Cuenta:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.gridwidth = 2;
        comboCuenta = new JComboBox<>();
        comboCuenta.setEnabled(false);
        panelBusqueda.add(comboCuenta, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        lblClienteInfo = new JLabel("Ingrese un ID para buscar");
        lblClienteInfo.setForeground(Color.GRAY);
        lblClienteInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        panelBusqueda.add(lblClienteInfo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        btnVerMovimientos = new JButton("Ver Movimientos");
        btnVerMovimientos.setBackground(new Color(52, 152, 219));
        btnVerMovimientos.setForeground(Color.WHITE);
        btnVerMovimientos.setFocusPainted(false);
        btnVerMovimientos.setEnabled(false);
        btnVerMovimientos.addActionListener(e -> cargarMovimientos());
        panelBusqueda.add(btnVerMovimientos, gbc);
        
        // Panel de resultados con tabla
        JPanel panelResultados = new JPanel(new BorderLayout(10, 10));
        panelResultados.setBackground(Color.WHITE);
        panelResultados.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(155, 89, 182), 2),
            "Movimientos de la Cuenta"));
        
        // Crear tabla
        String[] columnas = {"Fecha/Hora", "Tipo", "Monto", "Saldo Resultante", "Motivo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaMovimientos = new JTable(modeloTabla);
        tablaMovimientos.setFont(new Font("Arial", Font.PLAIN, 11));
        tablaMovimientos.setRowHeight(30);
        tablaMovimientos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaMovimientos.getTableHeader().setBackground(new Color(155, 89, 182));
        tablaMovimientos.getTableHeader().setForeground(Color.WHITE);
        
        // Ajustar anchos de columna
        tablaMovimientos.getColumnModel().getColumn(0).setPreferredWidth(150);
        tablaMovimientos.getColumnModel().getColumn(1).setPreferredWidth(80);
        tablaMovimientos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tablaMovimientos.getColumnModel().getColumn(3).setPreferredWidth(120);
        tablaMovimientos.getColumnModel().getColumn(4).setPreferredWidth(200);
        
        JScrollPane scrollPane = new JScrollPane(tablaMovimientos);
        scrollPane.setPreferredSize(new Dimension(750, 300));
        panelResultados.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(Color.WHITE);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setPreferredSize(new Dimension(150, 35));
        btnCerrar.setBackground(new Color(149, 165, 166));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> dispose());
        
        panelBotones.add(btnCerrar);
        
        // Panel central
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBackground(Color.WHITE);
        panelCentral.add(panelBusqueda, BorderLayout.NORTH);
        panelCentral.add(panelResultados, BorderLayout.CENTER);
        
        // Agregar componentes
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private void configurarVentana() {
        pack();
        setSize(800, 600);
        setLocationRelativeTo(getParent());
        setResizable(true);
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
                btnVerMovimientos.setEnabled(false);
            } else {
                for (Titularidad t : titularidades) {
                    Cuenta c = t.getCuenta();
                    comboCuenta.addItem(c.getNumeroCuenta() + " - " + c.getTipoCuenta());
                }
                comboCuenta.setEnabled(true);
                btnVerMovimientos.setEnabled(true);
            }
        } else {
            clienteSeleccionado = null;
            lblClienteInfo.setText("✗ Cliente no encontrado");
            lblClienteInfo.setForeground(Color.RED);
            comboCuenta.removeAllItems();
            comboCuenta.setEnabled(false);
            btnVerMovimientos.setEnabled(false);
            JOptionPane.showMessageDialog(this,
                "No se encontró ningún cliente con ese ID",
                "Cliente No Encontrado",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void cargarMovimientos() {
        if (clienteSeleccionado == null || comboCuenta.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this,
                "Seleccione una cuenta válida",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        
        int index = comboCuenta.getSelectedIndex();
        Cuenta cuentaSeleccionada = clienteSeleccionado.getTitularidades().get(index).getCuenta();
        
        ArrayList<Transaccion> movimientos = cuentaSeleccionada.consultarMovimientos();
        
        if (movimientos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No hay movimientos registrados en esta cuenta",
                "Sin Movimientos",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Simular saldo para cada transacción (calcular hacia atrás desde el saldo actual)
        double saldoActual = cuentaSeleccionada.getSaldo();
        
        // Cargar movimientos (del más reciente al más antiguo)
        for (int i = movimientos.size() - 1; i >= 0; i--) {
            Transaccion t = movimientos.get(i);
            String tipo = t instanceof Deposito ? "DEPÓSITO" : "RETIRO";
            String monto = "S/ " + String.format("%.2f", t.getMonto());
            
            // Calcular el saldo después de esta transacción
            double saldoDespues = saldoActual;
            
            Object[] fila = {
                t.getFecha().toString(),
                tipo,
                monto,
                "S/ " + String.format("%.2f", saldoDespues),
                t.getMotivo()
            };
            
            modeloTabla.insertRow(0, fila); // Insertar al principio para orden cronológico
            
            // Ajustar saldo para la siguiente iteración
            if (t instanceof Deposito) {
                saldoActual -= t.getMonto();
            } else {
                saldoActual += t.getMonto();
            }
        }
    }
}
