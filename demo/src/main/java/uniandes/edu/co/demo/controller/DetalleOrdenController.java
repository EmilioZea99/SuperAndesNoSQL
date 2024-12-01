package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.DetalleOrden;
import uniandes.edu.co.demo.repository.DetalleOrdenRepository;

import java.util.List;

@RestController
@RequestMapping("/detallesOrden")
public class DetalleOrdenController {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    // Crear un nuevo detalle de orden
    @PostMapping("/new/save")
    public ResponseEntity<String> crearDetalleOrden(@RequestBody DetalleOrden detalleOrden) {
        try {
            detalleOrdenRepository.save(detalleOrden);
            return new ResponseEntity<>("Detalle de orden creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el detalle de orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los detalles de una orden por ID de la orden de compra
    @GetMapping("/orden/{ordenCompraId}")
    public ResponseEntity<List<DetalleOrden>> obtenerDetallesPorOrdenCompraId(@PathVariable("ordenCompraId") String ordenCompraId) {
        try {
            List<DetalleOrden> detalles = detalleOrdenRepository.buscarDetallesPorOrdenCompraId(ordenCompraId);
            if (!detalles.isEmpty()) {
                return ResponseEntity.ok(detalles);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar todos los detalles de una orden de compra
    @DeleteMapping("/orden/{ordenCompraId}/delete")
    public ResponseEntity<String> eliminarDetallesPorOrdenCompraId(@PathVariable("ordenCompraId") String ordenCompraId) {
        try {
            detalleOrdenRepository.eliminarDetallesPorOrdenCompraId(ordenCompraId);
            return new ResponseEntity<>("Detalles de orden eliminados exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar los detalles de orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
