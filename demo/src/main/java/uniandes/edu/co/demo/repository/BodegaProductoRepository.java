package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.BodegaProducto;

public interface BodegaProductoRepository extends MongoRepository<BodegaProducto, String> {

    // Consultar todas las relaciones Bodega-Producto
    @Query(value = "{}")
    List<BodegaProducto> buscarTodasLasRelacionesBodegaProducto();

    // Consultar una relación Bodega-Producto por su ID
    @Query("{_id: ?0}")
    BodegaProducto buscarPorId(String id);

    // Consultar productos por ID de bodega
    @Query("{bodegaId: ?0}")
    List<BodegaProducto> buscarProductosPorBodegaId(String bodegaId);

    // Consultar bodegas por ID de producto
    @Query("{productoId: ?0}")
    List<BodegaProducto> buscarBodegasPorProductoId(String productoId);

    // Crear una nueva relación Bodega-Producto
    @Query("{ $insert: { _id: ?0, bodegaId: ?1, productoId: ?2, cantidad: ?3, costoPromedio: ?4 } }")
    void insertarBodegaProducto(String id, String bodegaId, String productoId, int cantidad, double costoPromedio);

    // Actualizar una relación Bodega-Producto por su ID
    @Query("{ _id: ?0 }")
    @Update("{ $set: { bodegaId: ?1, productoId: ?2, cantidad: ?3, costoPromedio: ?4 }}")
    void actualizarBodegaProducto(String id, String bodegaId, String productoId, int cantidad, double costoPromedio);

    // Eliminar una relación Bodega-Producto por su ID
    @Query(value = "{_id: ?0}", delete = true)
    void eliminarBodegaProductoPorId(String id);

    @Query("{ 'bodegaId': { $in: ?0 } }")
    List<BodegaProducto> buscarProductosPorSucursalId(List<String> bodegaIds);
}
