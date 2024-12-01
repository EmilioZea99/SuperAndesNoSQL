package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.BodegaProducto;
import uniandes.edu.co.demo.repository.BodegaProductoRepository;

import java.util.List;

@RestController
@RequestMapping("/bodegaProducto")
public class BodegaProductoController {

    @Autowired
    private BodegaProductoRepository bodegaProductoRepository;

    // Crear una nueva relación Bodega-Producto
    @PostMapping("/new/save")
    public ResponseEntity<String> crearBodegaProducto(@RequestBody BodegaProducto bodegaProducto) {
        try {
            bodegaProductoRepository.save(bodegaProducto);
            return new ResponseEntity<>("Relación Bodega-Producto creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la relación Bodega-Producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una relación Bodega-Producto existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarBodegaProducto(@PathVariable("id") String id, @RequestBody BodegaProducto bodegaProducto) {
        try {
            bodegaProductoRepository.actualizarBodegaProducto(
                id,
                bodegaProducto.getBodegaId(),
                bodegaProducto.getProductoId(),
                bodegaProducto.getCantidad(),
                bodegaProducto.getCostoPromedio()
            );
            return new ResponseEntity<>("Relación Bodega-Producto actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la relación Bodega-Producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las relaciones Bodega-Producto
    @GetMapping("")
    public ResponseEntity<List<BodegaProducto>> obtenerTodasLasRelacionesBodegaProducto() {
        try {
            List<BodegaProducto> relaciones = bodegaProductoRepository.buscarTodasLasRelacionesBodegaProducto();
            return ResponseEntity.ok(relaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una relación Bodega-Producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<BodegaProducto> obtenerBodegaProductoPorId(@PathVariable("id") String id) {
        try {
            BodegaProducto relacion = bodegaProductoRepository.buscarPorId(id);
            if (relacion != null) {
                return ResponseEntity.ok(relacion);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar una relación Bodega-Producto por su ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarBodegaProducto(@PathVariable("id") String id) {
        try {
            bodegaProductoRepository.eliminarBodegaProductoPorId(id);
            return new ResponseEntity<>("Relación Bodega-Producto eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la relación Bodega-Producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los productos en una bodega específica
    @GetMapping("/bodega/{bodegaId}")
    public ResponseEntity<List<BodegaProducto>> obtenerProductosPorBodegaId(@PathVariable("bodegaId") String bodegaId) {
        try {
            List<BodegaProducto> productos = bodegaProductoRepository.buscarProductosPorBodegaId(bodegaId);
            if (!productos.isEmpty()) {
                return ResponseEntity.ok(productos);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener todas las bodegas que almacenan un producto específico
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<BodegaProducto>> obtenerBodegasPorProductoId(@PathVariable("productoId") String productoId) {
        try {
            List<BodegaProducto> bodegas = bodegaProductoRepository.buscarBodegasPorProductoId(productoId);
            if (!bodegas.isEmpty()) {
                return ResponseEntity.ok(bodegas);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
