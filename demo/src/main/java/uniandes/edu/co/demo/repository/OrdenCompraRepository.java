package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.DetalleOrden;
import uniandes.edu.co.demo.modelo.OrdenCompra;

public interface OrdenCompraRepository extends MongoRepository<OrdenCompra, String> {

    // Consultar todas las órdenes de compra
    @Query(value = "{}")
    List<OrdenCompra> buscarTodasLasOrdenesCompra();

    // Consultar una orden de compra por su ID
    @Query("{_id: ?0}")
    OrdenCompra buscarPorId(String id);

    // Crear una nueva orden de compra
    @Query("{ $insert: { _id: ?0, sucursalId: ?1, proveedorId: ?2, fechaCreacion: ?3, fechaEsperadaEntrega: ?4, estado: ?5, detalles: ?6 } }")
    void insertarOrdenCompra(String id, String sucursalId, String proveedorId, String fechaCreacion, String fechaEsperadaEntrega, String estado, List<DetalleOrden> detalles);
}
