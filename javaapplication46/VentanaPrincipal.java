import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {
    private Sistema sistema;
    private Empleado empleadoActivo;
    private Empleado empleadoATM;
    
    // Componentes de la interfaz
    private JPanel panelPrincipal;
    private JMenuBar menuBar;
    private JLabel lblTitulo;
    private JLabel lblBienvenida;
    
    public VentanaPrincipal(Sistema sistema, Empleado empleadoActivo, Empleado empleadoATM) {
        this.sistema = sistema;
        this.empleadoActivo = empleadoActivo;
        this.empleadoATM = empleadoATM;
        
        initComponents();
        configurarVentana();
    }
    
    private void initComponents() {
        // Configuración de la ventana
        setTitle("Sistema Bancario - BCP");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con degradado
        panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(41, 128, 185), 
                                                    0, getHeight(), new Color(109, 213, 250));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        
        // Panel superior con título
        JPanel panelSuperior = new JPanel();
        panelSuperior.setOpaque(false);
        panelSuperior.setLayout(new BorderLayout());
        
        lblTitulo = new JLabel("SISTEMA BANCARIO ", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        lblBienvenida = new JLabel("Empleado: " + empleadoActivo.getNombreCompleto(), SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.PLAIN, 14));
        lblBienvenida.setForeground(Color.WHITE);
        
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        panelSuperior.add(lblBienvenida, BorderLayout.CENTER);
        
        // Panel central con botones
        JPanel panelCentro = new JPanel();
        panelCentro.setOpaque(false);
        panelCentro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        
        // Crear botones principales
        JButton btnRegistrarCliente = crearBoton("Registrar Cliente"), 
        JButton btnRegistrarCuenta = crearBoton("Registrar Cuenta");
        JButton btnDeposito = crearBoton("Realizar Depósito" );
        JButton btnRetiro = crearBoton("Realizar Retiro" );
        JButton btnConsultaSaldo = crearBoton("Consultar Saldo");
        JButton btnMovimientos = crearBoton("Ver Movimientos");
        JButton btnClientes = crearBoton("Ver Clientes");
        JButton btnEmpleados = crearBoton("Ver Empleados");
        JButton btnCajero = crearBoton("Cajero Automático");
        JButton btnSalir = crearBoton("Salir");
        
        // Agregar botones en grid
        gbc.gridx = 0; gbc.gridy = 0;
        panelCentro.add(btnRegistrarCliente, gbc);
        
        gbc.gridx = 1;
        panelCentro.add(btnRegistrarCuenta, gbc);
        
        gbc.gridx = 2;
        panelCentro.add(btnDeposito, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panelCentro.add(btnRetiro, gbc);
        
        gbc.gridx = 1;
        panelCentro.add(btnConsultaSaldo, gbc);
        
        gbc.gridx = 2;
        panelCentro.add(btnMovimientos, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panelCentro.add(btnClientes, gbc);
        
        gbc.gridx = 1;
        panelCentro.add(btnEmpleados, gbc);
        
        gbc.gridx = 2;
        panelCentro.add(btnCajero, gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.gridwidth = 1;
        panelCentro.add(btnSalir, gbc);
        
        // Agregar listeners
        btnRegistrarCliente.addActionListener(e -> abrirRegistroCliente());
        btnRegistrarCuenta.addActionListener(e -> abrirRegistroCuenta());
        btnDeposito.addActionListener(e -> abrirDeposito());
        btnRetiro.addActionListener(e -> abrirRetiro());
        btnConsultaSaldo.addActionListener(e -> abrirConsultaSaldo());
        btnMovimientos.addActionListener(e -> abrirMovimientos());
        btnClientes.addActionListener(e -> abrirListaClientes());
        btnEmpleados.addActionListener(e -> abrirListaEmpleados());
        btnCajero.addActionListener(e -> abrirCajeroAutomatico());
        btnSalir.addActionListener(e -> salir());
        
        // Panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setOpaque(false);
        JLabel lblFooter = new JLabel("© 2025 banco - Sistema de Gestión Bancaria", SwingConstants.CENTER);
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));
        panelInferior.add(lblFooter);
        
        // Agregar paneles
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        add(panelPrincipal);
        
        // Crear menú
        crearMenu();
    }
    
    private JButton crearBoton(String texto, String icono) {
        JButton btn = new JButton("<html><center>" + icono + "<br>" + texto + "</center></html>");
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(200, 80));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(41, 128, 185));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Efecto hover
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(52, 152, 219));
                btn.setForeground(new Color(10, 100, 185));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(new Color(41, 128, 185));
            }
        });
        
        return btn;
    }
    
    private void crearMenu() {
        menuBar = new JMenuBar();
        
        // Menú Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> salir());
        menuArchivo.add(itemSalir);
        
        // Menú Clientes
        JMenu menuClientes = new JMenu("Clientes");
        JMenuItem itemRegistrarCliente = new JMenuItem("Registrar Cliente");
        JMenuItem itemVerClientes = new JMenuItem("Ver Clientes");
        itemRegistrarCliente.addActionListener(e -> abrirRegistroCliente());
        itemVerClientes.addActionListener(e -> abrirListaClientes());
        menuClientes.add(itemRegistrarCliente);
        menuClientes.add(itemVerClientes);
        
        // Menú Cuentas
        JMenu menuCuentas = new JMenu("Cuentas");
        JMenuItem itemRegistrarCuenta = new JMenuItem("Registrar Cuenta");
        JMenuItem itemConsultaSaldo = new JMenuItem("Consultar Saldo");
        itemRegistrarCuenta.addActionListener(e -> abrirRegistroCuenta());
        itemConsultaSaldo.addActionListener(e -> abrirConsultaSaldo());
        menuCuentas.add(itemRegistrarCuenta);
        menuCuentas.add(itemConsultaSaldo);
        
        // Menú Transacciones
        JMenu menuTransacciones = new JMenu("Transacciones");
        JMenuItem itemDeposito = new JMenuItem("Depósito");
        JMenuItem itemRetiro = new JMenuItem("Retiro");
        JMenuItem itemMovimientos = new JMenuItem("Ver Movimientos");
        itemDeposito.addActionListener(e -> abrirDeposito());
        itemRetiro.addActionListener(e -> abrirRetiro());
        itemMovimientos.addActionListener(e -> abrirMovimientos());
        menuTransacciones.add(itemDeposito);
        menuTransacciones.add(itemRetiro);
        menuTransacciones.addSeparator();
        menuTransacciones.add(itemMovimientos);
        
        // Menú Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcerca = new JMenuItem("Acerca de");
        itemAcerca.addActionListener(e -> mostrarAcercaDe());
        menuAyuda.add(itemAcerca);
        
        menuBar.add(menuArchivo);
        menuBar.add(menuClientes);
        menuBar.add(menuCuentas);
        menuBar.add(menuTransacciones);
        menuBar.add(menuAyuda);
        
        setJMenuBar(menuBar);
    }
    
    private void configurarVentana() {
        // Configuraciones adicionales
        setResizable(true);
        setMinimumSize(new Dimension(800, 500));
    }
    
    // Métodos para abrir ventanas
    private void abrirRegistroCliente() {
        VentanaRegistroCliente ventana = new VentanaRegistroCliente(this, sistema);
        ventana.setVisible(true);
    }
    
    private void abrirRegistroCuenta() {
        VentanaRegistroCuenta ventana = new VentanaRegistroCuenta(this, sistema);
        ventana.setVisible(true);
    }
    
    private void abrirDeposito() {
        VentanaDeposito ventana = new VentanaDeposito(this, sistema, empleadoActivo);
        ventana.setVisible(true);
    }
    
    private void abrirRetiro() {
        VentanaRetiro ventana = new VentanaRetiro(this, sistema, empleadoActivo);
        ventana.setVisible(true);
    }
    
    private void abrirConsultaSaldo() {
        VentanaConsultaSaldo ventana = new VentanaConsultaSaldo(this, sistema);
        ventana.setVisible(true);
    }
    
    private void abrirMovimientos() {
        VentanaMovimientos ventana = new VentanaMovimientos(this, sistema);
        ventana.setVisible(true);
    }
    
    private void abrirListaClientes() {
        VentanaListaClientes ventana = new VentanaListaClientes(this, sistema);
        ventana.setVisible(true);
    }
    
    private void abrirListaEmpleados() {
        VentanaListaEmpleados ventana = new VentanaListaEmpleados(this, sistema);
        ventana.setVisible(true);
    }
    
    private void abrirCajeroAutomatico() {
        VentanaCajeroAutomatico ventana = new VentanaCajeroAutomatico(this, sistema, empleadoATM);
        ventana.setVisible(true);
    }
    
    private void mostrarAcercaDe() {
        JOptionPane.showMessageDialog(this,
            "Sistema Bancario BCP\nVersión 1.0\n\n" +
            "Sistema de gestión bancaria completo\n" +
            "con interfaz gráfica.\n\n" +
            "© 2025 Todos los derechos reservados",
            "Acerca de",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void salir() {
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea salir del sistema?",
            "Confirmar Salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    // Getters
    public Sistema getSistema() {
        return sistema;
    }
    
    public Empleado getEmpleadoActivo() {
        return empleadoActivo;
    }
}
