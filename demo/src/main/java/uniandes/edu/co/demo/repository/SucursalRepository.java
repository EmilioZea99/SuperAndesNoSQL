package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Sucursal;

import java.util.List;

public interface SucursalRepository extends MongoRepository<Sucursal, String> {

    // Consultar todas las sucursales
    @Query("{}")
    List<Sucursal> buscarTodasLasSucursales();

    // Consultar una sucursal por su ID
    @Query("{_id: ?0}")
    Sucursal buscarPorId(String id);

    // Consultar sucursales por ciudad
    @Query("{ciudad: ?0}")
    List<Sucursal> buscarSucursalesPorCiudad(String ciudad);

    // Crear una nueva sucursal
    @Query("{ $insert: { _id: ?0, nombre: ?1, ciudad: ?2, direccion: ?3, telefono: ?4, tamano: ?5 } }")
    void insertarSucursal(String id, String nombre, String ciudad, String direccion, String telefono, int tamano);

    // Actualizar una sucursal por su ID
    @Query("{ _id: ?0 }")
    @Update("{ $set: { nombre: ?1, ciudad: ?2, direccion: ?3, telefono: ?4, tamano: ?5 }}")
    void actualizarSucursal(String id, String nombre, String ciudad, String direccion, String telefono, int tamano);

    // Eliminar una sucursal por su ID
    @Query(value = "{_id: ?0}", delete = true)
    void eliminarSucursalPorId(String id);
}
