import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Clase principal para iniciar el sistema bancario con interfaz gráfica.
 * Esta clase inicializa el sistema con datos de prueba y lanza la ventana principal.
 */
public class mainGUI {
    
    public static void main(String[] args) {
        // Configurar Look and Feel del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Ejecutar en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            inicializarSistema();
        });
    }
    
    private static void inicializarSistema() {
        // Crear sistema
        Sistema sistema = new Sistema("BANCO BCP", "CV2");
        
        // ===== CREAR EMPLEADOS =====
        Empleado emp1 = new Empleado("Carlos", "Pérez", "912067512", "carlosP@gmail.com",
                LocalDate.of(1990, 6, 12), "E001", LocalDate.now(), 2500.0, "Cajas");
        sistema.registrarPersona(emp1);
        
        Empleado emp2 = new Empleado("José", "Zimmerman", "994453665", "joseZ@gmail.com",
                LocalDate.of(1985, 8, 28), "E002", LocalDate.now(), 3500.0, "Administración");
        sistema.registrarPersona(emp2);
        
        Empleado emp3 = new Empleado("María", "González", "987654321", "mariaG@gmail.com",
                LocalDate.of(1992, 3, 15), "E003", LocalDate.now(), 3000.0, "Servicio al Cliente");
        sistema.registrarPersona(emp3);
        
        // Empleado especial para el Cajero Automático
        Empleado empleadoATM = new Empleado("Sistema", "ATM", "N/A", "atm@sistema.com",
                LocalDate.of(2023, 1, 1), "EATM", LocalDate.now(), 0.0, "AUTOMÁTICO");
        sistema.registrarPersona(empleadoATM);
        
        // ===== CREAR CLIENTES CON CUENTAS =====
        
        // Cliente 1
        Cliente cli1 = new Cliente("Ana", "Zapana", "956564871", "anaZ@gmail.com",
                LocalDate.of(1995, 4, 25), "C001", "1234");
        sistema.registrarPersona(cli1);
        sistema.crearCuenta(cli1, "Ahorros", 1000);
        sistema.crearCuenta(cli1, "Corriente", 2500);
        
        // Cliente 2
        Cliente cli2 = new Cliente("Julio", "Mamani", "944575848", "julioM@gmail.com",
                LocalDate.of(1992, 8, 17), "C002", "5678");
        sistema.registrarPersona(cli2);
        sistema.crearCuenta(cli2, "Corriente", 2000);
        
        // Cliente 3
        Cliente cli3 = new Cliente("Javier", "Valdez", "956563371", "jvalde@gmail.com",
                LocalDate.of(2000, 5, 21), "C003", "1235");
        sistema.registrarPersona(cli3);
        sistema.crearCuenta(cli3, "Ahorros", 1500);
        sistema.crearCuenta(cli3, "Plazo Fijo", 5000);
        
        // Cliente 4
        Cliente cli4 = new Cliente("Sofía", "Torres", "923456789", "sofiaT@gmail.com",
                LocalDate.of(1998, 11, 8), "C004", "9876");
        sistema.registrarPersona(cli4);
        sistema.crearCuenta(cli4, "Ahorros", 3000);
        
        // Cliente 5
        Cliente cli5 = new Cliente("Roberto", "Díaz", "912345678", "robertoD@gmail.com",
                LocalDate.of(1985, 2, 14), "C005", "4321");
        sistema.registrarPersona(cli5);
        sistema.crearCuenta(cli5, "Corriente", 4500);
        sistema.crearCuenta(cli5, "CTS", 8000);
        
        // ===== CREAR ADMINISTRADOR (opcional para futuras funcionalidades) =====
        Administrador admin1 = new Administrador("Luis", "Ramírez", "998877665", 
                "luisR@bcp.com", LocalDate.of(1980, 7, 20), "ADM001", 1);
        sistema.registrarPersona(admin1);
        
        // ===== MOSTRAR SPLASH SCREEN =====
        mostrarSplashScreen();
        
        // ===== INICIAR VENTANA PRINCIPAL =====
        VentanaPrincipal ventana = new VentanaPrincipal(sistema, emp1, empleadoATM);
        ventana.setVisible(true);
    }
    
    /**
     * Muestra una pantalla de bienvenida antes de cargar el sistema completo
     */
    private static void mostrarSplashScreen() {
        JWindow splash = new JWindow();
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createCompoundBorder());
        
        // Logo y título
        JLabel lblLogo = new JLabel("🏦", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Arial", Font.PLAIN, 80));
        
        JLabel lblTitulo = new JLabel("SISTEMA BANCARIO BCP", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(41, 128, 185));
        
        JLabel lblVersion = new JLabel("Versión 2.0 - Interfaz Gráfica", SwingConstants.CENTER);
        lblVersion.setFont(new Font("Arial", Font.PLAIN, 14));
        lblVersion.setForeground(Color.GRAY);
        
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setStringPainted(true);
        progressBar.setString("Cargando sistema...");
        
        JPanel panelCentro = new JPanel(new GridLayout(3, 1, 10, 10));
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentro.add(lblLogo);
        panelCentro.add(lblTitulo);
        panelCentro.add(lblVersion);
        
        panel.setBackground(Color.WHITE);
        panel.add(panelCentro, BorderLayout.CENTER);
        panel.add(progressBar, BorderLayout.SOUTH);
        
        splash.setContentPane(panel);
        splash.setSize(500, 350);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        
        // Simular carga
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        splash.dispose();
    }
}
