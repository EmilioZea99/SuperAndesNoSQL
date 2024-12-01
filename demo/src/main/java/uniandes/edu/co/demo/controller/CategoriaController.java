package uniandes.edu.co.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Categoria;
import uniandes.edu.co.demo.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear una nueva categoría
    @PostMapping("/new/save")
    public ResponseEntity<String> crearCategoria(@RequestBody Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            return new ResponseEntity<>("Categoría creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la categoría: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una categoría existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarCategoria(@PathVariable("id") String id, @RequestBody Categoria categoria) {
        try {
            categoriaRepository.actualizarCategoria(
                id,
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getCaracteristicasAlmacenamiento()
            );
            return new ResponseEntity<>("Categoría actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la categoría: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las categorías
    @GetMapping("")
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        try {
            List<Categoria> categorias = categoriaRepository.buscarTodasLasCategorias();
            return ResponseEntity.ok(categorias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una categoría por ID o nombre
    @GetMapping("/{identificador}")
    public ResponseEntity<Categoria> obtenerCategoriaPorIdONombre(@PathVariable("identificador") String identificador) {
        try {
            Categoria categoria = categoriaRepository.buscarPorIdONombre(identificador);
            if (categoria != null) {
                return ResponseEntity.ok(categoria);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar una categoría por su ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarCategoria(@PathVariable("id") String id) {
        try {
            categoriaRepository.eliminarCategoriaPorId(id);
            return new ResponseEntity<>("Categoría eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la categoría: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
