import java.time.LocalDateTime; // Clase moderna para fecha y hora

public abstract class Transaccion {
    protected String codigo;
    protected double monto;
    // Atributo actualizado: Usando LocalDateTime en lugar de java.util.Date
    protected LocalDateTime fecha; 
    protected Empleado empleadoRegistra;
    protected Cuenta cuentaRegistra;
    protected String motivo;

    // Constructor actualizado: La fecha es ahora LocalDateTime
    public Transaccion(String codigo, double monto, LocalDateTime fecha, Empleado empleadoRegistra, Cuenta cuentaRegistra, String motivo) {
        this.codigo = codigo;
        this.monto = monto;
        this.fecha = fecha;
        this.empleadoRegistra = empleadoRegistra;
        this.cuentaRegistra = cuentaRegistra;
        this.motivo = motivo;
    }

    public abstract boolean procesar(Cuenta cuentaRegistra);

    public String getCodigo() {
        return codigo;
    }

    public double getMonto() {
        return monto;
    }

    // Getter actualizado: Retorna LocalDateTime
    public LocalDateTime getFecha() {
        return fecha;
    }

    public Empleado getEmpleadoRegistra() {
        return empleadoRegistra;
    }

    public Cuenta getCuentaRegistra() {
        return cuentaRegistra;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return "Transacción{" +
                "Código='" + codigo + '\'' +
                ", Monto=" + monto +
                ", Fecha=" + fecha + // LocalDateTime tiene un buen formato por defecto
                ", Empleado='" + empleadoRegistra.getNombreCompleto() + '\'' +
                ", Cuenta=" + cuentaRegistra.getNumeroCuenta() +
                ", Motivo='" + motivo + '\'' +
                '}';
    }
}
