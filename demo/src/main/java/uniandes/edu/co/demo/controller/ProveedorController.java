package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Proveedor;
import uniandes.edu.co.demo.repository.ProveedorRepository;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Crear un nuevo proveedor
    @PostMapping("/new/save")
    public ResponseEntity<String> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.save(proveedor);
            return new ResponseEntity<>("Proveedor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un proveedor existente
    @PostMapping("/{nit}/edit/save")
    public ResponseEntity<String> actualizarProveedor(@PathVariable("nit") String nit, @RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.actualizarProveedor(
                nit,
                proveedor.getNombre(),
                proveedor.getDireccion(),
                proveedor.getNombreContacto(),
                proveedor.getTelefonoContacto()
            );
            return new ResponseEntity<>("Proveedor actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los proveedores
    @GetMapping("")
    public ResponseEntity<List<Proveedor>> obtenerTodosLosProveedores() {
        try {
            List<Proveedor> proveedores = proveedorRepository.buscarTodosLosProveedores();
            return ResponseEntity.ok(proveedores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener un proveedor por NIT
    @GetMapping("/{nit}")
    public ResponseEntity<Proveedor> obtenerProveedorPorNit(@PathVariable("nit") String nit) {
        try {
            Proveedor proveedor = proveedorRepository.buscarPorNit(nit);
            if (proveedor != null) {
                return ResponseEntity.ok(proveedor);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar un proveedor por su NIT
    @DeleteMapping("/{nit}/delete")
    public ResponseEntity<String> eliminarProveedor(@PathVariable("nit") String nit) {
        try {
            proveedorRepository.eliminarProveedorPorNit(nit);
            return new ResponseEntity<>("Proveedor eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
