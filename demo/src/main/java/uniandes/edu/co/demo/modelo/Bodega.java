package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="bodegas")
public class Bodega {

    @Id
    private String id;
    private String nombre;
    private int tamano;
    private int capacidad;
    private double costoPromedio;
    private String sucursalId; // ID de la sucursal a la que pertenece la bodega

    public Bodega(String id, String nombre, int tamano, int capacidad, double costoPromedio, String sucursalId) {
        this.id = id;
        this.nombre = nombre;
        this.tamano = tamano;
        this.capacidad = capacidad;
        this.costoPromedio = costoPromedio;
        this.sucursalId = sucursalId;
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

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(double costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public String getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(String sucursalId) {
        this.sucursalId = sucursalId;
    }
}
