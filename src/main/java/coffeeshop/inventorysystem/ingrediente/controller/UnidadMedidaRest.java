package coffeeshop.inventorysystem.ingrediente.controller;

import coffeeshop.inventorysystem.ingrediente.dto.UnidadMedidaRequest;
import coffeeshop.inventorysystem.ingrediente.model.UnidadMedida;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/unidad-medida")
@Tag(name = "Unidades de Medida", description = "CRUD de unidades de medida para ingredientes")
public interface UnidadMedidaRest {

    @PostMapping(path = "/create")
    @Operation(summary = "Crear unidad de medida", description = "Registra una nueva unidad de medida (ej: g, ml, kg, L, unidades). Requiere rol ADMIN.")
    @ApiResponse(responseCode = "200", description = "Unidad de medida creada exitosamente")
    ResponseEntity<String> create(@Valid @RequestBody UnidadMedidaRequest request);

    @PutMapping(path = "/update")
    @Operation(summary = "Actualizar unidad de medida", description = "Actualiza el nombre de una unidad de medida existente. Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidad de medida actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Unidad de medida no encontrada")
    })
    ResponseEntity<String> update(@Valid @RequestBody UnidadMedidaRequest request);

    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Eliminar unidad de medida", description = "Elimina una unidad de medida. Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidad de medida eliminada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Unidad de medida no encontrada")
    })
    ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping(path = "/get/{id}")
    @Operation(summary = "Obtener unidad de medida por ID", description = "Retorna una unidad de medida específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidad de medida encontrada"),
            @ApiResponse(responseCode = "400", description = "Unidad de medida no encontrada")
    })
    ResponseEntity<UnidadMedida> getById(@PathVariable Integer id);

    @GetMapping(path = "/getAll")
    @Operation(summary = "Listar unidades de medida", description = "Retorna todas las unidades de medida registradas.")
    @ApiResponse(responseCode = "200", description = "Lista de unidades de medida")
    ResponseEntity<List<UnidadMedida>> getAll();
}
