package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "ordenesCompra")
public class OrdenCompra {

    @Id
    private String id;
    private String sucursalId;
    private String proveedorId;
    private Date fechaCreacion;
    private Date fechaEsperadaEntrega;
    private String estado;  // "vigente", "entregada", "anulada"
    private List<DetalleOrden> detalles; // Detalles embebidos dentro de la orden

    // Constructor
    public OrdenCompra(String id, String sucursalId, String proveedorId, Date fechaCreacion, Date fechaEsperadaEntrega, String estado, List<DetalleOrden> detalles) {
        this.id = id;
        this.sucursalId = sucursalId;
        this.proveedorId = proveedorId;
        this.fechaCreacion = fechaCreacion;
        this.fechaEsperadaEntrega = fechaEsperadaEntrega;
        this.estado = estado;
        this.detalles = detalles;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(String sucursalId) {
        this.sucursalId = sucursalId;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaEsperadaEntrega() {
        return fechaEsperadaEntrega;
    }

    public void setFechaEsperadaEntrega(Date fechaEsperadaEntrega) {
        this.fechaEsperadaEntrega = fechaEsperadaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetalleOrden> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrden> detalles) {
        this.detalles = detalles;
    }
}
