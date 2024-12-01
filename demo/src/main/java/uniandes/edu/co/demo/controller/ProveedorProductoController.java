package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.ProveedorProducto;
import uniandes.edu.co.demo.repository.ProveedorProductoRepository;

import java.util.List;

@RestController
@RequestMapping("/proveedorProductos")
public class ProveedorProductoController {

    @Autowired
    private ProveedorProductoRepository proveedorProductoRepository;

    // Crear un nuevo registro de ProveedorProducto
    @PostMapping("/new/save")
    public ResponseEntity<String> crearProveedorProducto(@RequestBody ProveedorProducto proveedorProducto) {
        try {
            proveedorProductoRepository.save(proveedorProducto);
            return new ResponseEntity<>("ProveedorProducto creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear ProveedorProducto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los registros de ProveedorProducto
    @GetMapping("")
    public ResponseEntity<List<ProveedorProducto>> obtenerTodos() {
        try {
            List<ProveedorProducto> proveedorProductos = proveedorProductoRepository.buscarTodos();
            return ResponseEntity.ok(proveedorProductos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener un ProveedorProducto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorProducto> obtenerPorId(@PathVariable("id") String id) {
        try {
            ProveedorProducto proveedorProducto = proveedorProductoRepository.buscarPorId(id);
            if (proveedorProducto != null) {
                return ResponseEntity.ok(proveedorProducto);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar un ProveedorProducto por su ID
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarProveedorProducto(@PathVariable("id") String id, @RequestBody ProveedorProducto proveedorProducto) {
        try {
            proveedorProductoRepository.actualizarProveedorProducto(
                    id,
                    proveedorProducto.getProveedorId(),
                    proveedorProducto.getProductoId(),
                    proveedorProducto.getPrecioAcuerdo()
            );
            return new ResponseEntity<>("ProveedorProducto actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar ProveedorProducto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un ProveedorProducto por ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarProveedorProducto(@PathVariable("id") String id) {
        try {
            proveedorProductoRepository.eliminarProveedorProductoPorId(id);
            return new ResponseEntity<>("ProveedorProducto eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar ProveedorProducto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
