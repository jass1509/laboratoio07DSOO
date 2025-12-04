import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaConsultaSaldo extends JDialog {
    private Sistema sistema;
    
    // Componentes
    private JTextField txtIdCliente;
    private JButton btnBuscar, btnCerrar;
    private JLabel lblClienteInfo;
    private JTable tablaCuentas;
    private DefaultTableModel modeloTabla;
    private Cliente clienteSeleccionado;
    
    public VentanaConsultaSaldo(Frame parent, Sistema sistema) {
        super(parent, "Consultar Saldo", true);
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
        JLabel lblTitulo = new JLabel("📊 Consulta de Saldo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(52, 152, 219));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new GridBagLayout());
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
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
        lblClienteInfo = new JLabel("Ingrese un ID para buscar");
        lblClienteInfo.setForeground(Color.GRAY);
        lblClienteInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        panelBusqueda.add(lblClienteInfo, gbc);
        
        // Panel de resultados con tabla
        JPanel panelResultados = new JPanel(new BorderLayout(10, 10));
        panelResultados.setBackground(Color.WHITE);
        panelResultados.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
            "Cuentas del Cliente"));
        
        // Crear tabla
        String[] columnas = {"Número de Cuenta", "Tipo", "Saldo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };
        
        tablaCuentas = new JTable(modeloTabla);
        tablaCuentas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaCuentas.setRowHeight(25);
        tablaCuentas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaCuentas.getTableHeader().setBackground(new Color(52, 152, 219));
        tablaCuentas.getTableHeader().setForeground(Color.WHITE);
        
        // Ajustar anchos de columna
        tablaCuentas.getColumnModel().getColumn(0).setPreferredWidth(150);
        tablaCuentas.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaCuentas.getColumnModel().getColumn(2).setPreferredWidth(100);
        tablaCuentas.getColumnModel().getColumn(3).setPreferredWidth(100);
        
        JScrollPane scrollPane = new JScrollPane(tablaCuentas);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        panelResultados.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de resumen
        JPanel panelResumen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelResumen.setBackground(Color.WHITE);
        JLabel lblResumen = new JLabel("Total en todas las cuentas: S/ 0.00");
        lblResumen.setFont(new Font("Arial", Font.BOLD, 14));
        lblResumen.setForeground(new Color(52, 152, 219));
        panelResumen.add(lblResumen);
        panelResultados.add(panelResumen, BorderLayout.SOUTH);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(Color.WHITE);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setPreferredSize(new Dimension(150, 35));
        btnCerrar.setBackground(new Color(149, 165, 166));
        btnCerrar.setForeground(new Color(60,60,60));
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
        setSize(700, 550);
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
            lblClienteInfo.setText("✓ Cliente: " + clienteSeleccionado.getNombreCompleto() + 
                                  " | Edad: " + clienteSeleccionado.calcularEdad() + " años");
            lblClienteInfo.setForeground(new Color(46, 204, 113));
            
            cargarCuentas();
        } else {
            clienteSeleccionado = null;
            lblClienteInfo.setText("✗ Cliente no encontrado");
            lblClienteInfo.setForeground(Color.RED);
            modeloTabla.setRowCount(0);
            JOptionPane.showMessageDialog(this,
                "No se encontró ningún cliente con ese ID",
                "Cliente No Encontrado",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void cargarCuentas() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        
        ArrayList<Titularidad> titularidades = clienteSeleccionado.getTitularidades();
        
        if (titularidades.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "El cliente no tiene cuentas registradas",
                "Sin Cuentas",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        double totalSaldos = 0;
        
        for (Titularidad t : titularidades) {
            Cuenta c = t.getCuenta();
            String estado = c.getSaldo() > 0 ? "Activa" : "Sin Fondos";
            
            Object[] fila = {
                c.getNumeroCuenta(),
                c.getTipoCuenta(),
                "S/ " + String.format("%.2f", c.getSaldo()),
                estado
            };
            
            modeloTabla.addRow(fila);
            totalSaldos += c.getSaldo();
        }
        
        // Actualizar resumen
        Component[] components = ((JPanel)((JPanel)getContentPane().getComponent(0))
            .getComponent(1)).getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                for (Component c : panel.getComponents()) {
                    if (c instanceof JLabel && ((JLabel)c).getText().contains("Total")) {
                        ((JLabel)c).setText("Total en todas las cuentas: S/ " + 
                                          String.format("%.2f", totalSaldos));
                    }
                }
            }
        }
    }
}
