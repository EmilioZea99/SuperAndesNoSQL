package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.ProveedorProducto;

public interface ProveedorProductoRepository extends MongoRepository<ProveedorProducto, String> {

    // Consultar todos los registros de ProveedorProducto
    @Query("{}")
    List<ProveedorProducto> buscarTodos();

    // Consultar por ID
    @Query("{_id: ?0}")
    ProveedorProducto buscarPorId(String id);

    // Crear un nuevo registro ProveedorProducto
    @Query("{ $insert: { _id: ?0, proveedorId: ?1, productoId: ?2, precioAcuerdo: ?3 } }")
    void insertarProveedorProducto(String id, String proveedorId, String productoId, double precioAcuerdo);

    // Actualizar un registro ProveedorProducto por ID
    @Query("{ _id: ?0 }")
    @Update("{ $set: { proveedorId: ?1, productoId: ?2, precioAcuerdo: ?3 }}")
    void actualizarProveedorProducto(String id, String proveedorId, String productoId, double precioAcuerdo);

    // Eliminar un registro ProveedorProducto por ID
    @Query(value = "{_id: ?0}", delete = true)
    void eliminarProveedorProductoPorId(String id);
}
