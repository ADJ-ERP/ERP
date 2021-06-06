package database.tables;

public class AlbaranDB {
    private final String codigo;
    private final String fecha;
    private final Double cantidadKG;
    private final String descripcion;
    private final Double precioKG;
    private final Double precioTotal;

    public AlbaranDB(String codigo, String fecha, Double cantidadKG, String descripcion, Double precioKG, Double precioTotal) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cantidadKG = cantidadKG;
        this.descripcion = descripcion;
        this.precioKG = precioKG;
        this.precioTotal = precioTotal;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public Double getCantidadKG() {
        return cantidadKG;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecioKG() {
        return precioKG;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }
}
