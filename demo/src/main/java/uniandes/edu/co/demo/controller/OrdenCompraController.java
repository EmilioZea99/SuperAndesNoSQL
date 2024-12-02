package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.*;
import uniandes.edu.co.demo.repository.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ordenesCompra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Crear una nueva orden de compra
    @PostMapping("/new/save")
public ResponseEntity<String> crearOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
    try {
        // Verificar que la sucursal proporcionada exista
        Sucursal sucursal = sucursalRepository.findById(ordenCompra.getSucursalId()).orElse(null);
        if (sucursal == null) {
            return new ResponseEntity<>("La sucursal con el ID proporcionado no existe", HttpStatus.NOT_FOUND);
        }

        // Verificar que el proveedor proporcionado exista
        Proveedor proveedor = proveedorRepository.findById(ordenCompra.getProveedorId()).orElse(null);
        if (proveedor == null) {
            return new ResponseEntity<>("El proveedor con el ID proporcionado no existe", HttpStatus.NOT_FOUND);
        }

        // Verificar que todos los productos en el detalle existan
        for (DetalleOrden detalle : ordenCompra.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProductoId()).orElse(null);
            if (producto == null) {
                return new ResponseEntity<>("El producto con el ID " + detalle.getProductoId() + " no existe", HttpStatus.NOT_FOUND);
            }
        }

        // Asignar la fecha de creación como la fecha actual del sistema
        ordenCompra.setFechaCreacion(new Date());
        ordenCompra.setEstado("vigente");  // Estado inicial de la orden

        // Guardar la orden de compra
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
