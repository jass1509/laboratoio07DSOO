package javaapplication46;
import java.util.Date;

public abstract class Transaccion {
    protected String idTransaccion;
    protected Date fecha;
    protected double monto;

    public abstract void procesar();
}
