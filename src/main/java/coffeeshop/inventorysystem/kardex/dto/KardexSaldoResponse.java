package coffeeshop.inventorysystem.kardex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO que representa el saldo actual de un ingrediente en el inventario.
 * <p>
 * Se usa como respuesta al consultar el stock disponible de un ingrediente.
 * </p>
 *
 * @since 1.0
 */
@Data
@AllArgsConstructor
@Schema(description = "Saldo actual de un ingrediente en el inventario")
public class KardexSaldoResponse {

    @Schema(description = "ID del ingrediente", example = "1")
    private Integer ingredienteId;

    @Schema(description = "Nombre del ingrediente", example = "Harina")
    private String ingredienteNombre;

    @Schema(description = "Cantidad actual en stock", example = "100.0")
    private Double saldoActual;

}
