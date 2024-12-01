package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Proveedor;

import java.util.List;

public interface ProveedorRepository extends MongoRepository<Proveedor, String> {

    // Consultar todos los proveedores
    @Query("{}")
    List<Proveedor> buscarTodosLosProveedores();

    // Consultar proveedor por su NIT
    @Query("{_id: ?0}")
    Proveedor buscarPorNit(String nit);

    // Crear un nuevo proveedor
    @Query("{ $insert: { _id: ?0, nombre: ?1, direccion: ?2, nombreContacto: ?3, telefonoContacto: ?4 } }")
    void insertarProveedor(String nit, String nombre, String direccion, String nombreContacto, String telefonoContacto);

    // Actualizar un proveedor por su NIT
    @Query("{ _id: ?0 }")
    @Update("{ $set: { nombre: ?1, direccion: ?2, nombreContacto: ?3, telefonoContacto: ?4 }}")
    void actualizarProveedor(String nit, String nombre, String direccion, String nombreContacto, String telefonoContacto);

    // Eliminar un proveedor por su NIT
    @Query(value = "{_id: ?0}", delete = true)
    void eliminarProveedorPorNit(String nit);
}
