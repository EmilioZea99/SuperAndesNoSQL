package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.DetalleOrden;

import java.util.List;

public interface DetalleOrdenRepository extends MongoRepository<DetalleOrden, String> {

    // Consultar todos los detalles de una orden por el ID de la orden de compra
    @Query("{'ordenCompraId': ?0}")
    List<DetalleOrden> buscarDetallesPorOrdenCompraId(String ordenCompraId);

    // Eliminar detalles de una orden de compra por el ID de la orden
    @Query(value = "{'ordenCompraId': ?0}", delete = true)
    void eliminarDetallesPorOrdenCompraId(String ordenCompraId);
}
