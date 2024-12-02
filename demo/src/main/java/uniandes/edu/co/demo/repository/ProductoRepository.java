package uniandes.edu.co.demo.repository;

import java.util.List;
import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {

    // Consultar todos los productos
    @Query(value = "{}")
    List<Producto> buscarTodosLosProductos();

    // Consultar un producto por su código de barras
    @Query("{codigoBarras: ?0}")
    Producto buscarPorCodigoBarras(String codigoBarras);

    // Consultar productos por ID de categoría
    @Query("{categoriaId: ?0}")
    List<Producto> buscarProductosPorCategoriaId(String categoriaId);

    // Crear un nuevo producto (manteniendo la referencia)
    @Query("{ $insert: { codigoBarras: ?0, nombre: ?1, costoBodega: ?2, precioVenta: ?3, presentacion: ?4, cantidadPresentacion: ?5, unidadMedida: ?6, volumenEmpaque: ?7, pesoEmpaque: ?8, fechaExpiracion: ?9, categoriaId: ?10, bodegasIds: ?11, proveedoresIds: ?12 } }")
    void insertarProducto(String codigoBarras, String nombre, double costoBodega, double precioVenta, String presentacion, int cantidadPresentacion, String unidadMedida, int volumenEmpaque, int pesoEmpaque, String fechaExpiracion, String categoriaId, List<String> bodegasIds, List<String> proveedoresIds);

    // Actualizar un producto por su código de barras
    @Query("{ codigoBarras: ?0 }")
    @Update("{ $set: { nombre: ?1, costoBodega: ?2, precioVenta: ?3, presentacion: ?4, cantidadPresentacion: ?5, unidadMedida: ?6, volumenEmpaque: ?7, pesoEmpaque: ?8, fechaExpiracion: ?9, categoriaId: ?10, bodegasIds: ?11, proveedoresIds: ?12 }}")
    void actualizarProducto(String codigoBarras, String nombre, double costoBodega, double precioVenta, String presentacion, int cantidadPresentacion, String unidadMedida, int volumenEmpaque, int pesoEmpaque, String fechaExpiracion, String categoriaId, List<String> bodegasIds, List<String> proveedoresIds);

    // Eliminar un producto por su código de barras
    @Query(value = "{codigoBarras: ?0}", delete = true)
    void eliminarProductoPorCodigoBarras(String codigoBarras);

    @Query("{ 'precioVenta': { $gte: ?0, $lte: ?1 } }")
    List<Producto> buscarPorRangoDePrecio(double precioMin, double precioMax);

    @Query("{ 'fechaExpiracion': { $gte: ?0 } }")
    List<Producto> buscarPorFechaExpiracionPosterior(Date fecha);

    @Query("{ 'fechaExpiracion': { $lte: ?0 } }")
    List<Producto> buscarPorFechaExpiracionInferior(Date fecha);

    @Query("{ 'categoriaId': ?0 }")
    List<Producto> buscarPorCategoriaId(String categoriaId);

    @Query("{ 'bodegasIds': ?0 }")
    List<Producto> buscarPorBodegaId(String bodegaId);
}
