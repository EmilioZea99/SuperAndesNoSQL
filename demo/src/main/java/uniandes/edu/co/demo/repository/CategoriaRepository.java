package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {

    // Consultar todas las categorías
    @Query("{}")
    List<Categoria> buscarTodasLasCategorias();

    // Consultar una categoría por su ID o nombre
    @Query("{$or: [{_id: ?0}, {nombre: ?0}]}")
    Categoria buscarPorIdONombre(String identificador);

    // Buscar una categoría por nombre
    @Query("{ 'nombre': ?0 }")
    List<Categoria> buscarPorNombre(String nombre);

    // Crear una nueva categoría
    @Query("{ $insert: { _id: ?0, nombre: ?1, descripcion: ?2, caracteristicasAlmacenamiento: ?3 } }")
    void insertarCategoria(String id, String nombre, String descripcion, String caracteristicasAlmacenamiento);

    // Actualizar una categoría por su ID
    @Query("{ _id: ?0 }")
    @Update("{ $set: { nombre: ?1, descripcion: ?2, caracteristicasAlmacenamiento: ?3 }}")
    void actualizarCategoria(String id, String nombre, String descripcion, String caracteristicasAlmacenamiento);

    // Eliminar una categoría por su ID
    @Query(value = "{_id: ?0}", delete = true)
    void eliminarCategoriaPorId(String id);
}
