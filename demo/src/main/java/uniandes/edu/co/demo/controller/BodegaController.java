package uniandes.edu.co.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.*;
import uniandes.edu.co.demo.repository.*;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Crear una nueva bodega
    @PostMapping("/new/save")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega) {
        try {
            // Validar que la sucursalId proporcionada exista
            Sucursal sucursal = sucursalRepository.buscarPorId(bodega.getSucursalId());
            if (sucursal == null) {
                return new ResponseEntity<>("La sucursal con el ID proporcionado no existe", HttpStatus.NOT_FOUND);
            }

            bodegaRepository.save(bodega);
            return new ResponseEntity<>("Bodega creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una bodega existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarBodega(@PathVariable("id") String id, @RequestBody Bodega bodega) {
        try {
            // Validar que la sucursalId proporcionada exista
            Sucursal sucursal = sucursalRepository.buscarPorId(bodega.getSucursalId());
            if (sucursal == null) {
                return new ResponseEntity<>("La sucursal con el ID proporcionado no existe", HttpStatus.NOT_FOUND);
            }

            bodegaRepository.actualizarBodega(
                id,
                bodega.getNombre(),
                bodega.getTamano(),
                bodega.getCapacidad(),
                bodega.getCostoPromedio(),
                bodega.getSucursalId()
            );
            return new ResponseEntity<>("Bodega actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las bodegas
    @GetMapping("")
    public ResponseEntity<List<Bodega>> obtenerTodasLasBodegas() {
        try {
            List<Bodega> bodegas = bodegaRepository.buscarTodasLasBodegas();
            return ResponseEntity.ok(bodegas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una bodega por ID
    @GetMapping("/{id}")
    public ResponseEntity<Bodega> obtenerBodegaPorId(@PathVariable("id") String id) {
        try {
            Bodega bodega = bodegaRepository.buscarPorId(id);
            if (bodega != null) {
                return ResponseEntity.ok(bodega);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar una bodega por su ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarBodega(@PathVariable("id") String id) {
        try {
            Bodega bodega = bodegaRepository.buscarPorId(id);
            if (bodega == null) {
                return new ResponseEntity<>("Bodega no encontrada", HttpStatus.NOT_FOUND);
            }
            bodegaRepository.delete(bodega);
            return new ResponseEntity<>("Bodega eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener el inventario de productos en una sucursal (RFC2)
    @GetMapping("/inventario/{sucursalId}")
    public ResponseEntity<List<Map<String, Object>>> obtenerInventarioPorSucursal(@PathVariable("sucursalId") String sucursalId) {
        try {
            // Buscar todas las bodegas asociadas a la sucursal
            List<Bodega> bodegas = bodegaRepository.buscarBodegasPorSucursalId(sucursalId);

            if (bodegas.isEmpty()) {
                return new ResponseEntity("No se encontraron bodegas para la sucursal proporcionada", HttpStatus.NOT_FOUND);
            }

            List<Map<String, Object>> inventario = new ArrayList<>();

            // Iterar por cada bodega y buscar los productos almacenados en ella
            for (Bodega bodega : bodegas) {
                List<Producto> productos = productoRepository.buscarPorBodegaId(bodega.getId());

                for (Producto producto : productos) {
                    // Crear un mapa con la información del producto en la bodega
                    // Crear un mapa con la información del producto en la bodega
                    Map<String, Object> inventarioProducto = new HashMap<>();
                    inventarioProducto.put("nombreBodega", bodega.getNombre());  // Añadir el nombre de la bodega
                    inventarioProducto.put("nombreProducto", producto.getNombre());
                    inventarioProducto.put("cantidadActual", producto.getCantidadPresentacion());
                    inventarioProducto.put("cantidadMinima", 10);  // Cantidad mínima requerida (esto podría ser un campo dinámico en Bodega)
                    inventarioProducto.put("costoPromedio", bodega.getCostoPromedio());


                    // Añadir el producto al inventario
                    inventario.add(inventarioProducto);
                }
            }

            return ResponseEntity.ok(inventario);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

    

