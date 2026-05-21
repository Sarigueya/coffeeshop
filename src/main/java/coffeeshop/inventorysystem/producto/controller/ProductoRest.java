package coffeeshop.inventorysystem.producto.controller;

import coffeeshop.inventorysystem.producto.dto.ProductoRequest;
import coffeeshop.inventorysystem.producto.model.Producto;
import coffeeshop.inventorysystem.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/producto")
@Tag(name = "Productos", description = "CRUD de productos con recetas e ingredientes")
public interface ProductoRest {

    @PostMapping(path = "/create")
    @Operation(summary = "Crear producto", description = "Registra un nuevo producto con su receta opcional (lista de ingredientes y cantidades). Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Ingredientes no encontrados o datos inválidos")
    })
    ResponseEntity<String> create(@Valid @RequestBody ProductoRequest request);

    @PutMapping(path = "/update")
    @Operation(summary = "Actualizar producto", description = "Actualiza los datos de un producto y su receta. Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Producto o ingredientes no encontrados")
    })
    ResponseEntity<String> update(@Valid @RequestBody ProductoRequest request);

    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto del sistema. Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Producto no encontrado")
    })
    ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping(path = "/get/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Retorna los datos de un producto específico, incluyendo su receta.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    ResponseEntity<Producto> getById(@PathVariable Integer id);

    @GetMapping(path = "/getAll")
    @Operation(summary = "Listar productos", description = "Retorna todos los productos registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de productos")
    ResponseEntity<List<Producto>> getAll();
}
