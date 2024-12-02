package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Producto;
import uniandes.edu.co.demo.modelo.Categoria;
import uniandes.edu.co.demo.repository.ProductoRepository;
import uniandes.edu.co.demo.repository.CategoriaRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear un nuevo producto
    @PostMapping("/new/save")
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto) {
        try {
            // Verificar que la categoría proporcionada exista
            Categoria categoria = categoriaRepository.findById(producto.getCategoriaId()).orElse(null);
            if (categoria == null) {
                return new ResponseEntity<>("La categoría con el ID proporcionado no existe", HttpStatus.NOT_FOUND);
            }

            productoRepository.save(producto);
            return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un producto existente
    @PutMapping("/{codigoBarras}/edit")
    public ResponseEntity<String> actualizarProducto(@PathVariable("codigoBarras") String codigoBarras, @RequestBody Producto producto) {
        try {
            Producto productoExistente = productoRepository.findById(codigoBarras).orElse(null);
            if (productoExistente == null) {
                return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
            }

            // Verificar que la categoría proporcionada exista
            Categoria categoria = categoriaRepository.findById(producto.getCategoriaId()).orElse(null);
            if (categoria == null) {
                return new ResponseEntity<>("La categoría con el ID proporcionado no existe", HttpStatus.NOT_FOUND);
            }

            // Actualizar los campos del producto
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setCostoBodega(producto.getCostoBodega());
            productoExistente.setPrecioVenta(producto.getPrecioVenta());
            productoExistente.setPresentacion(producto.getPresentacion());
            productoExistente.setCantidadPresentacion(producto.getCantidadPresentacion());
            productoExistente.setUnidadMedida(producto.getUnidadMedida());
            productoExistente.setVolumenEmpaque(producto.getVolumenEmpaque());
            productoExistente.setPesoEmpaque(producto.getPesoEmpaque());
            productoExistente.setFechaExpiracion(producto.getFechaExpiracion());
            productoExistente.setCategoriaId(producto.getCategoriaId());
            productoExistente.setBodegasIds(producto.getBodegasIds());
            productoExistente.setProveedoresIds(producto.getProveedoresIds());

            productoRepository.save(productoExistente);
            return new ResponseEntity<>("Producto actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los productos
    @GetMapping("")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        try {
            List<Producto> productos = productoRepository.buscarTodosLosProductos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener un producto por código de barras
    @GetMapping("/{codigoBarras}")
    public ResponseEntity<Producto> obtenerProductoPorCodigoBarras(@PathVariable("codigoBarras") String codigoBarras) {
        try {
            Producto producto = productoRepository.buscarPorCodigoBarras(codigoBarras);
            if (producto != null) {
                return ResponseEntity.ok(producto);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar un producto por su código de barras
    @DeleteMapping("/{codigoBarras}/delete")
    public ResponseEntity<String> eliminarProducto(@PathVariable("codigoBarras") String codigoBarras) {
        try {
            productoRepository.eliminarProductoPorCodigoBarras(codigoBarras);
            return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener productos por ID de categoría
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoriaId(@PathVariable("categoriaId") String categoriaId) {
        try {
            List<Producto> productos = productoRepository.buscarProductosPorCategoriaId(categoriaId);
            if (!productos.isEmpty()) {
                return ResponseEntity.ok(productos);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Métodos adicionales para consultas avanzadas
    @GetMapping("/rango-precio")
    public List<Producto> obtenerProductosPorRangoDePrecio(@RequestParam double precioMin, @RequestParam double precioMax) {
        return productoRepository.buscarPorRangoDePrecio(precioMin, precioMax);
    }

    @GetMapping("/fecha-expiracion-posterior")
    public List<Producto> obtenerProductosPorFechaExpiracionPosterior(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        return productoRepository.buscarPorFechaExpiracionPosterior(fecha);
    }

    @GetMapping("/fecha-expiracion-inferior")
    public List<Producto> obtenerProductosPorFechaExpiracionInferior(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        return productoRepository.buscarPorFechaExpiracionInferior(fecha);
    }

    @GetMapping("/bodega")
    public List<Producto> obtenerProductosPorBodega(@RequestParam String bodegaId) {
        return productoRepository.buscarPorBodegaId(bodegaId);
    }
}
