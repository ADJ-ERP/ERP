package database.tables;

public class ClientDB {
    private String nombre;
    private String CIF;
    private String telefono;
    private String correo;
    private String direccion;

    public ClientDB(String nombre, String CIF, String telefono, String correo, String direccion) {
        this.nombre = nombre;
        this.CIF = CIF;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCIF() {
        return CIF;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }
}
