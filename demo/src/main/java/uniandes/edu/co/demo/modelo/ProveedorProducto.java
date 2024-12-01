package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProveedorProducto")
public class ProveedorProducto {

    @Id
    private String id;
    private String proveedorId;  // Referencia al proveedor
    private String productoId;   // Referencia al producto
    private double precioAcuerdo;

    public ProveedorProducto() {}

    public ProveedorProducto(String id, String proveedorId, String productoId, double precioAcuerdo) {
        this.id = id;
        this.proveedorId = proveedorId;
        this.productoId = productoId;
        this.precioAcuerdo = precioAcuerdo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public double getPrecioAcuerdo() {
        return precioAcuerdo;
    }

    public void setPrecioAcuerdo(double precioAcuerdo) {
        this.precioAcuerdo = precioAcuerdo;
    }
}
