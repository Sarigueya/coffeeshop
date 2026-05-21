package coffeeshop.inventorysystem.kardex.controller;

import coffeeshop.inventorysystem.kardex.dto.KardexSaldoResponse;
import coffeeshop.inventorysystem.kardex.dto.MovimientoRequest;
import coffeeshop.inventorysystem.kardex.dto.VentaRequest;
import coffeeshop.inventorysystem.kardex.model.Kardex;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/kardex")
@Tag(name = "Kardex", description = "Control de inventario, movimientos y ventas")
public interface KardexRest {

    @PostMapping(path = "/entrada")
    @Operation(summary = "Registrar entrada", description = "Registra una entrada de stock para un ingrediente (movimiento tipo Compra).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entrada registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Ingrediente o movimiento no encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    ResponseEntity<String> registrarEntrada(@Valid @RequestBody MovimientoRequest request);

    @PostMapping(path = "/salida")
    @Operation(summary = "Registrar salida", description = "Registra una salida de stock por daño o pérdida (movimiento tipo Daño). Alerta si el stock queda bajo el mínimo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Salida registrada exitosamente (puede incluir alerta de stock bajo)"),
            @ApiResponse(responseCode = "400", description = "Stock insuficiente, ingrediente o movimiento no encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    ResponseEntity<String> registrarSalida(@Valid @RequestBody MovimientoRequest request);

    @PostMapping(path = "/vender")
    @Operation(summary = "Registrar venta", description = "Procesa una venta descontando ingredientes de la receta del producto del stock.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Stock insuficiente, producto sin receta, o ingredientes faltantes"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    ResponseEntity<String> vender(@Valid @RequestBody VentaRequest request);

    @GetMapping(path = "/ingrediente/{ingredienteId}")
    @Operation(summary = "Historial por ingrediente", description = "Retorna todos los movimientos de kardex para un ingrediente específico.")
    @ApiResponse(responseCode = "200", description = "Historial de movimientos")
    ResponseEntity<List<Kardex>> getByIngrediente(@PathVariable Integer ingredienteId);

    @GetMapping(path = "/getAll")
    @Operation(summary = "Listar movimientos", description = "Retorna todos los movimientos de kardex registrados.")
    @ApiResponse(responseCode = "200", description = "Lista completa de movimientos de kardex")
    ResponseEntity<List<Kardex>> getAll();

    @GetMapping(path = "/getSaldos")
    @Operation(summary = "Obtener saldos", description = "Retorna el saldo actual de cada ingrediente en el inventario.")
    @ApiResponse(responseCode = "200", description = "Lista de saldos por ingrediente")
    ResponseEntity<List<KardexSaldoResponse>> getSaldos();
}
