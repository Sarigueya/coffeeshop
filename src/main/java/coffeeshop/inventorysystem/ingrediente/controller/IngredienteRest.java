package coffeeshop.inventorysystem.ingrediente.controller;

import coffeeshop.inventorysystem.ingrediente.dto.IngredienteRequest;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/ingrediente")
@Tag(name = "Ingredientes", description = "CRUD de ingredientes y su gestión")
public interface IngredienteRest {

    @PostMapping(path = "/create")
    @Operation(summary = "Crear ingrediente", description = "Registra un nuevo ingrediente con su unidad de medida. Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Unidad de medida no encontrada o datos inválidos")
    })
    ResponseEntity<String> create(@Valid @RequestBody IngredienteRequest request);

    @PutMapping(path = "/update")
    @Operation(summary = "Actualizar ingrediente", description = "Actualiza los datos de un ingrediente existente. Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Ingrediente o unidad de medida no encontrado")
    })
    ResponseEntity<String> update(@Valid @RequestBody IngredienteRequest request);

    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Eliminar ingrediente", description = "Elimina o desactiva un ingrediente. Si tiene movimientos asociados, solo se desactiva. Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente eliminado o desactivado"),
            @ApiResponse(responseCode = "400", description = "Ingrediente no encontrado")
    })
    ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping(path = "/get/{id}")
    @Operation(summary = "Obtener ingrediente por ID", description = "Retorna los datos de un ingrediente específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente encontrado"),
            @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado")
    })
    ResponseEntity<Ingrediente> getById(@PathVariable Integer id);

    @GetMapping(path = "/getAll")
    @Operation(summary = "Listar ingredientes", description = "Retorna todos los ingredientes registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de ingredientes")
    ResponseEntity<List<Ingrediente>> getAll();
}
