package database.tables;

public class PartidaDB {
    private final int numPartida;
    private final String fechaAlta;
    private final String tipo;
    private final String centroVenta;
    private final int numMatadero;
    private final String proveedor;
    private final int numExplotacion;
    private final String paisNacido;
    private final String paisSacrificado;
    private final String tipoAnimal;
    private final int totalAnimales;
    private final int delNum;
    private final int alNum;
    private final int totalKgBrutos;
    private final int porcenOreo;
    private final int totalKgNetos;
    private final int importeTotalCosto;
    private final String notas;

    public PartidaDB(int numPartida, String fechaAlta, String tipo, String centroVenta, int numMatadero, String proveedor, int numExplotacion, String paisNacido, String paisSacrificado, String tipoAnimal, int totalAnimales, int delNum, int alNum, int totalKgBrutos, int porcenOreo, int totalKgNetos, int importeTotalCosto, String notas) {
        this.numPartida = numPartida;
        this.fechaAlta = fechaAlta;
        this.tipo = tipo;
        this.centroVenta = centroVenta;
        this.numMatadero = numMatadero;
        this.proveedor = proveedor;
        this.numExplotacion = numExplotacion;
        this.paisNacido = paisNacido;
        this.paisSacrificado = paisSacrificado;
        this.tipoAnimal = tipoAnimal;
        this.totalAnimales = totalAnimales;
        this.delNum = delNum;
        this.alNum = alNum;
        this.totalKgBrutos = totalKgBrutos;
        this.porcenOreo = porcenOreo;
        this.totalKgNetos = totalKgNetos;
        this.importeTotalCosto = importeTotalCosto;
        this.notas = notas;
    }

    public int getNumPartida() {
        return numPartida;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCentroVenta() {
        return centroVenta;
    }

    public int getNumMatadero() {
        return numMatadero;
    }

    public String getProveedor() {
        return proveedor;
    }

    public int getNumExplotacion() {
        return numExplotacion;
    }

    public String getPaisNacido() {
        return paisNacido;
    }

    public String getPaisSacrificado() {
        return paisSacrificado;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public int getTotalAnimales() {
        return totalAnimales;
    }

    public int getDelNum() {
        return delNum;
    }

    public int getAlNum() {
        return alNum;
    }

    public int getTotalKgBrutos() {
        return totalKgBrutos;
    }

    public int getPorcenOreo() {
        return porcenOreo;
    }

    public int getTotalKgNetos() {
        return totalKgNetos;
    }

    public int getImporteTotalCosto() {
        return importeTotalCosto;
    }

    public String getNotas() {
        return notas;
    }

    @Override
    public String toString() {
        return "PartidaDB{" +
                "numPartida=" + numPartida +
                ", fechaAlta='" + fechaAlta + '\'' +
                ", tipo='" + tipo + '\'' +
                ", centroVenta='" + centroVenta + '\'' +
                ", numMatadero=" + numMatadero +
                ", proveedor='" + proveedor + '\'' +
                ", numExplotacion=" + numExplotacion +
                ", paisNacido='" + paisNacido + '\'' +
                ", paisSacrificado='" + paisSacrificado + '\'' +
                ", tipoAnimal='" + tipoAnimal + '\'' +
                ", totalAnimales=" + totalAnimales +
                ", delNum=" + delNum +
                ", alNum=" + alNum +
                ", totalKgBrutos=" + totalKgBrutos +
                ", porcenOreo=" + porcenOreo +
                ", totalKgNetos=" + totalKgNetos +
                ", importeTotalCosto=" + importeTotalCosto +
                ", notas='" + notas + '\'' +
                '}';
    }
}
