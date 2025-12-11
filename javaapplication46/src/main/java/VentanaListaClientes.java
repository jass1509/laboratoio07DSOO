import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// ===== VENTANA LISTA CLIENTES =====
class VentanaListaClientes extends JDialog {
    private Sistema sistema;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    
    public VentanaListaClientes(Frame parent, Sistema sistema) {
        super(parent, "Lista de Clientes", true);
        this.sistema = sistema;
        
        initComponents();
        configurarVentana();
        cargarClientes();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);
        
        // Título
        JLabel lblTitulo = new JLabel("Lista de Clientes Registrados", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(41, 128, 185));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Crear tabla
        String[] columnas = {"ID", "Nombre Completo", "Teléfono", "Email", "Edad", "Cuentas"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaClientes.setRowHeight(25);
        tablaClientes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaClientes.getTableHeader().setBackground(new Color(92, 159, 204));
        tablaClientes.getTableHeader().setForeground(new Color(41, 128, 185));
        
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        scrollPane.setPreferredSize(new Dimension(750, 400));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(Color.WHITE);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setPreferredSize(new Dimension(150, 35));
        btnCerrar.setBackground(new Color(149, 165, 166));
        btnCerrar.setForeground(new Color(41, 128, 185));
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> dispose());
        
        panelBotones.add(btnCerrar);
        
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private void configurarVentana() {
        pack();
        setSize(800, 550);
        setLocationRelativeTo(getParent());
        setResizable(true);
    }
    
    private void cargarClientes() {
        // Limpiar tabla antes de cargar
        modeloTabla.setRowCount(0);

        // Obtener lista de clientes directamente desde el sistema
        ArrayList<Cliente> clientes = sistema.obtenerClientes();

        for (Cliente c : clientes) {
            Object[] fila = {
                c.getIdCliente(),
                c.getNombreCompleto(),
                c.getTelefono(),
                c.getEmail(),
                c.calcularEdad() + " años",
                c.getTitularidades().size() + " cuenta(s)"
            };
            modeloTabla.addRow(fila);
        }
    }
}

// ===== VENTANA LISTA EMPLEADOS =====
class VentanaListaEmpleados extends JDialog {
    private Sistema sistema;
    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;
    
    public VentanaListaEmpleados(Frame parent, Sistema sistema) {
        super(parent, "Lista de Empleados", true);
        this.sistema = sistema;
        
        initComponents();
        configurarVentana();
        cargarEmpleados();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);
        
        // Título
        JLabel lblTitulo = new JLabel("Lista de Empleados", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(52, 73, 94));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Crear tabla
        String[] columnas = {"Código", "Nombre Completo", "Teléfono", "Email", "Departamento", "Salario"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaEmpleados.setRowHeight(25);
        tablaEmpleados.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaEmpleados.getTableHeader().setBackground(new Color(92, 159, 204));
        tablaEmpleados.getTableHeader().setForeground(new Color(41, 128, 185));
        
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(Color.WHITE);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setPreferredSize(new Dimension(150, 35));
        btnCerrar.setBackground(new Color(149, 165, 166));
        btnCerrar.setForeground(new Color(41, 128, 185));
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> dispose());
        
        panelBotones.add(btnCerrar);
        
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private void configurarVentana() {
        pack();
        setSize(850, 550);
        setLocationRelativeTo(getParent());
        setResizable(true);
    }
    
    private void cargarEmpleados() {
        ArrayList<String> empleados = sistema.generarReporte("empleados");
        
        for (String empInfo : empleados) {
            if (empInfo.contains("Empleado:")) {
                String[] partes = empInfo.split("\\|");
                if (partes.length >= 2) {
                    String codigo = partes[0].replace("Empleado: codigoEmpleado='", "").replace("'", "").trim();
                    
                    // Buscar empleado para obtener más información
                    Persona p = sistema.buscarPersona(codigo);
                    if (p instanceof Empleado) {
                        Empleado e = (Empleado) p;
                        Object[] fila = {
                            e.getCodigoEmpleado(),
                            e.getNombreCompleto(),
                            e.getTelefono(),
                            e.getEmail(),
                            empInfo.contains("departamento='") ? 
                                empInfo.substring(empInfo.indexOf("departamento='") + 14, 
                                empInfo.lastIndexOf("'")) : "N/A",
                            "S/ " + (empInfo.contains("salario=") ? 
                                empInfo.substring(empInfo.indexOf("salario=") + 8).split("\\|")[0].trim() : "0.00")
                        };
                        modeloTabla.addRow(fila);
                    }
                }
            }
        }
    }
}
