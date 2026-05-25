package coffeeshop.inventorysystem.kardex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * DTO que encapsula los datos para registrar un movimiento de inventario.
 * <p>
 * Puede representar tanto una entrada como una salida de stock,
 * dependiendo del endpoint al que se envía.
 * </p>
 *
 * @since 1.0
 */
@Data
@Schema(description = "Solicitud de movimiento de inventario (entrada o salida)")
public class MovimientoRequest {

    @Schema(description = "ID del ingrediente", example = "1")
    @NotNull
    private Integer ingredienteId;

    @Schema(description = "Cantidad a mover", example = "50.0")
    @NotNull
    @Positive
    private Double cantidad;
}
