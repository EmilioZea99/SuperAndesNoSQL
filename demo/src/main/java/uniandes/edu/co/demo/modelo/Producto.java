package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "productos")
public class Producto {

    @Id
    private String codigoBarras;
    private String nombre;
    private double costoBodega;
    private double precioVenta;
    private String presentacion;
    private int cantidadPresentacion;
    private String unidadMedida;
    private int volumenEmpaque;
    private int pesoEmpaque;
    private Date fechaExpiracion;
    private String categoriaId;  // Relación muchos a uno con Categoría
    private List<String> bodegasIds;  // Relación muchos a muchos con Bodega mediante BodegaProducto
    private List<String> proveedoresIds;  // Relación muchos a muchos con Proveedor mediante ProveedorProducto

    // Constructor vacío
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(String codigoBarras, String nombre, double costoBodega, double precioVenta,
                    String presentacion, int cantidadPresentacion, String unidadMedida,
                    int volumenEmpaque, int pesoEmpaque, Date fechaExpiracion,
                    String categoriaId, List<String> bodegasIds, List<String> proveedoresIds) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.costoBodega = costoBodega;
        this.precioVenta = precioVenta;
        this.presentacion = presentacion;
        this.cantidadPresentacion = cantidadPresentacion;
        this.unidadMedida = unidadMedida;
        this.volumenEmpaque = volumenEmpaque;
        this.pesoEmpaque = pesoEmpaque;
        this.fechaExpiracion = fechaExpiracion;
        this.categoriaId = categoriaId;
        this.bodegasIds = bodegasIds;
        this.proveedoresIds = proveedoresIds;
    }

    // Getters y Setters
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCostoBodega() {
        return costoBodega;
    }

    public void setCostoBodega(double costoBodega) {
        this.costoBodega = costoBodega;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public int getCantidadPresentacion() {
        return cantidadPresentacion;
    }

    public void setCantidadPresentacion(int cantidadPresentacion) {
        this.cantidadPresentacion = cantidadPresentacion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getVolumenEmpaque() {
        return volumenEmpaque;
    }

    public void setVolumenEmpaque(int volumenEmpaque) {
        this.volumenEmpaque = volumenEmpaque;
    }

    public int getPesoEmpaque() {
        return pesoEmpaque;
    }

    public void setPesoEmpaque(int pesoEmpaque) {
        this.pesoEmpaque = pesoEmpaque;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<String> getBodegasIds() {
        return bodegasIds;
    }

    public void setBodegasIds(List<String> bodegasIds) {
        this.bodegasIds = bodegasIds;
    }

    public List<String> getProveedoresIds() {
        return proveedoresIds;
    }

    public void setProveedoresIds(List<String> proveedoresIds) {
        this.proveedoresIds = proveedoresIds;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigoBarras='" + codigoBarras + '\'' +
                ", nombre='" + nombre + '\'' +
                ", costoBodega=" + costoBodega +
                ", precioVenta=" + precioVenta +
                ", presentacion='" + presentacion + '\'' +
                ", cantidadPresentacion=" + cantidadPresentacion +
                ", unidadMedida='" + unidadMedida + '\'' +
                ", volumenEmpaque=" + volumenEmpaque +
                ", pesoEmpaque=" + pesoEmpaque +
                ", fechaExpiracion=" + fechaExpiracion +
                ", categoriaId='" + categoriaId + '\'' +
                ", bodegasIds=" + bodegasIds +
                ", proveedoresIds=" + proveedoresIds +
                '}';
    }
}
