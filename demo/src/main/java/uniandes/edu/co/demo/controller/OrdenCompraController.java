package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.OrdenCompra;
import uniandes.edu.co.demo.repository.OrdenCompraRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ordenesCompra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    // Crear una nueva orden de compra
    @PostMapping("/new/save")
    public ResponseEntity<String> crearOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        try {
            ordenCompra.setFechaCreacion(new Date()); // La fecha de creación es la actual
            ordenCompra.setEstado("vigente"); // La orden se crea en estado "vigente"
            ordenCompraRepository.save(ordenCompra);
            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las órdenes de compra
    @GetMapping("")
    public ResponseEntity<List<OrdenCompra>> obtenerTodasLasOrdenesCompra() {
        try {
            List<OrdenCompra> ordenes = ordenCompraRepository.buscarTodasLasOrdenesCompra();
            return ResponseEntity.ok(ordenes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una orden de compra por ID (RF7)
    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> obtenerOrdenCompraPorId(@PathVariable("id") String id) {
        try {
            OrdenCompra orden = ordenCompraRepository.buscarPorId(id);
            if (orden != null) {
                return ResponseEntity.ok(orden);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar una orden de compra por su ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarOrdenCompra(@PathVariable("id") String id) {
        try {
            ordenCompraRepository.deleteById(id);
            return new ResponseEntity<>("Orden de compra eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
