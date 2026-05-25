package coffeeshop.inventorysystem.kardex.service;

import coffeeshop.inventorysystem.kardex.dto.KardexSaldoResponse;
import coffeeshop.inventorysystem.kardex.dto.MovimientoRequest;
import coffeeshop.inventorysystem.kardex.dto.VentaRequest;
import coffeeshop.inventorysystem.kardex.model.Kardex;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Servicio para la gestión del kardex de inventario.
 * <p>
 * Registra movimientos de entrada y salida de ingredientes,
 * procesa ventas de productos (descontando ingredientes de la receta),
 * consulta saldos actuales y genera reportes en PDF del inventario.
 * </p>
 *
 * @since 1.0
 */
public interface KardexService {

    /**
     * Registra una entrada de stock para un ingrediente.
     *
     * @param request datos del movimiento: ingrediente y cantidad
     * @return {@code 200 OK} si se registró exitosamente,
     *         {@code 400 BAD_REQUEST} si el ingrediente no existe
     */
    ResponseEntity<String> registrarEntrada(MovimientoRequest request);

    /**
     * Registra una salida de stock para un ingrediente.
     *
     * @param request datos del movimiento: ingrediente y cantidad
     * @return {@code 200 OK} si se registró exitosamente,
     *         {@code 400 BAD_REQUEST} si el ingrediente no existe o stock insuficiente
     */
    ResponseEntity<String> registrarSalida(MovimientoRequest request);

    /**
     * Procesa la venta de un producto.
     * <p>
     * Descuenta automáticamente del stock los ingredientes
     * definidos en la receta del producto.
     * </p>
     *
     * @param request datos de la venta: producto y cantidad
     * @return {@code 200 OK} si la venta se procesó,
     *         {@code 400 BAD_REQUEST} si hay errores de stock o receta
     */
    ResponseEntity<String> vender(VentaRequest request);

    /**
     * Obtiene todos los movimientos de kardex de un ingrediente.
     *
     * @param ingredienteId identificador del ingrediente
     * @return lista de movimientos del ingrediente
     */
    ResponseEntity<List<Kardex>> getByIngrediente(Integer ingredienteId);

    /**
     * Obtiene todos los movimientos de kardex registrados.
     *
     * @return lista completa de movimientos
     */
    ResponseEntity<List<Kardex>> getAll();

    /**
     * Obtiene el saldo actual de todos los ingredientes.
     *
     * @return lista con el saldo de cada ingrediente
     */
    ResponseEntity<List<KardexSaldoResponse>> getSaldos();

    /**
     * Genera un reporte en PDF del inventario actual.
     *
     * @return arreglo de bytes del archivo PDF generado
     */
    ResponseEntity<byte[]> generarReportePdf();
}
