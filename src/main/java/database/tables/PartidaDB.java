package database.tables;

public class PartidaDB {
    private int numPartida;
    private String fechaAlta;
    private String tipo;
    private String centroVenta;
    private int numMatadero;
    private String proveedor;
    private int numExplotacion;
    private String paisNacido;
    private String paisSacrificado;
    private String tipoAnimal;
    private int totalAnimales;
    private int delNum;
    private int alNum;
    private int totalKgBrutos;
    private int porcenOreo;
    private int totalKgNetos;
    private int importeTotalCosto;
    private String notas;

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

    public void setNumPartida(int numPartida) {
        this.numPartida = numPartida;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCentroVenta() {
        return centroVenta;
    }

    public void setCentroVenta(String centroVenta) {
        this.centroVenta = centroVenta;
    }

    public int getNumMatadero() {
        return numMatadero;
    }

    public void setNumMatadero(int numMatadero) {
        this.numMatadero = numMatadero;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getNumExplotacion() {
        return numExplotacion;
    }

    public void setNumExplotacion(int numExplotacion) {
        this.numExplotacion = numExplotacion;
    }

    public String getPaisNacido() {
        return paisNacido;
    }

    public void setPaisNacido(String paisNacido) {
        this.paisNacido = paisNacido;
    }

    public String getPaisSacrificado() {
        return paisSacrificado;
    }

    public void setPaisSacrificado(String paisSacrificado) {
        this.paisSacrificado = paisSacrificado;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public int getTotalAnimales() {
        return totalAnimales;
    }

    public void setTotalAnimales(int totalAnimales) {
        this.totalAnimales = totalAnimales;
    }

    public int getDelNum() {
        return delNum;
    }

    public void setDelNum(int delNum) {
        this.delNum = delNum;
    }

    public int getAlNum() {
        return alNum;
    }

    public void setAlNum(int alNum) {
        this.alNum = alNum;
    }

    public int getTotalKgBrutos() {
        return totalKgBrutos;
    }

    public void setTotalKgBrutos(int totalKgBrutos) {
        this.totalKgBrutos = totalKgBrutos;
    }

    public int getPorcenOreo() {
        return porcenOreo;
    }

    public void setPorcenOreo(int porcenOreo) {
        this.porcenOreo = porcenOreo;
    }

    public int getTotalKgNetos() {
        return totalKgNetos;
    }

    public void setTotalKgNetos(int totalKgNetos) {
        this.totalKgNetos = totalKgNetos;
    }

    public int getImporteTotalCosto() {
        return importeTotalCosto;
    }

    public void setImporteTotalCosto(int importeTotalCosto) {
        this.importeTotalCosto = importeTotalCosto;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
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
