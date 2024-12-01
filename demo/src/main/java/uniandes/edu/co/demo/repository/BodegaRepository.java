package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Bodega;

public interface BodegaRepository extends MongoRepository<Bodega, String> {

    // Consultar todas las bodegas
    @Query(value = "{}")
    List<Bodega> buscarTodasLasBodegas();

    // Consultar una bodega por su ID
    @Query("{_id: ?0}")
    Bodega buscarPorId(String id);

    // Consultar bodegas por ID de sucursal
    @Query("{sucursalId: ?0}")
    List<Bodega> buscarBodegasPorSucursalId(String sucursalId);

    // Crear una nueva bodega
    @Query("{ $insert: { _id: ?0, nombre: ?1, tamano: ?2, capacidad: ?3, costoPromedio: ?4, sucursalId: ?5 } }")
    void insertarBodega(String id, String nombre, int tamano, int capacidad, double costoPromedio, String sucursalId);

    // Actualizar una bodega por su ID
    @Query("{ _id: ?0 }")
    @Update("{ $set: { nombre: ?1, tamano: ?2, capacidad: ?3, costoPromedio: ?4, sucursalId: ?5 }}")
    void actualizarBodega(String id, String nombre, int tamano, int capacidad, double costoPromedio, String sucursalId);

    // Eliminar una bodega por su ID
    @Query(value = "{_id: ?0}", delete = true)
    void eliminarBodegaPorId(String id);
}
