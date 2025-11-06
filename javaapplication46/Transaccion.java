package javaapplication46;
import java.util.Date;

public abstract class Transaccion {
    protected String codigo;
    protected double monto;
    protected Date fecha;
    protected Empleado empleadoRegistra;
    protected Cuenta cuentaRegistra;
    protected String motivo;

    public Transaccion(String codigo, double monto, Date fecha, Empleado empleadoRegistra, Cuenta cuentaRegistra, String motivo) {
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

    public Date getFecha() {
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
                ", Fecha=" + fecha +
                ", Empleado='" + empleadoRegistra.getNombreCompleto() + '\'' +
                ", Cuenta=" + cuentaRegistra.getNumeroCuenta() +
                ", Motivo='" + motivo + '\'' +
                '}';
    }
}
