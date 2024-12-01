package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sucursales")
public class Sucursal {

    @Id
    private String id;
    private String nombre;
    private String ciudad;
    private String direccion;
    private String telefono;
    private int tamano;

    // Constructor vacío
    public Sucursal() {}

    // Constructor con parámetros
    public Sucursal(String id, String nombre, String ciudad, String direccion, String telefono, int tamano) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tamano = tamano;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    @Override
    public String toString() {
        return "Sucursal [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", direccion=" + direccion
                + ", telefono=" + telefono + ", tamano=" + tamano + "]";
    }
}
