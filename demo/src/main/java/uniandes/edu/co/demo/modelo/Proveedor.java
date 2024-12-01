package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proveedores")  // Nombre de la colección en MongoDB
public class Proveedor {

    @Id
    private String nit;  // Utilizamos el NIT como identificador único para los proveedores
    private String nombre;
    private String direccion;
    private String nombreContacto;
    private String telefonoContacto;

    // Constructor vacío
    public Proveedor() {}

    // Constructor con parámetros
    public Proveedor(String nit, String nombre, String direccion, String nombreContacto, String telefonoContacto) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
    }

    // Getters y Setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    @Override
    public String toString() {
        return "Proveedor [nit=" + nit + ", nombre=" + nombre + ", direccion=" + direccion + ", nombreContacto=" + nombreContacto
                + ", telefonoContacto=" + telefonoContacto + "]";
    }
}
