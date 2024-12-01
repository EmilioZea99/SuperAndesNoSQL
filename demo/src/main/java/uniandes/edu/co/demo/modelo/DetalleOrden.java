package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DetalleOrden")
public class DetalleOrden {

    @Id
    private String id;
    private String productoId; // ID del producto asociado
    private int cantidad;
    private double precio;

    // Constructor
    public DetalleOrden(String id, String productoId, int cantidad, double precio) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
