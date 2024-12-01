package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bodegaProducto")
public class BodegaProducto {

    @Id
    private String id;
    private String bodegaId; // ID de la bodega asociada
    private String productoId; // ID del producto asociado
    private int cantidad; // Cantidad disponible del producto en la bodega
    private double costoPromedio; // Costo promedio del producto en esa bodega

    // Constructor
    public BodegaProducto(String id, String bodegaId, String productoId, int cantidad, double costoPromedio) {
        this.id = id;
        this.bodegaId = bodegaId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.costoPromedio = costoPromedio;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBodegaId() {
        return bodegaId;
    }

    public void setBodegaId(String bodegaId) {
        this.bodegaId = bodegaId;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(double costoPromedio) {
        this.costoPromedio = costoPromedio;
    }
}
