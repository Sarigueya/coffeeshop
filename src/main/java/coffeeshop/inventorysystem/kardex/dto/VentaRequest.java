package coffeeshop.inventorysystem.kardex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * DTO que encapsula los datos para registrar una venta.
 * <p>
 * La venta consume automáticamente el stock de los ingredientes
 * asociados a la receta del producto.
 * </p>
 *
 * @since 1.0
 */
@Data
@Schema(description = "Solicitud de venta de producto")
public class VentaRequest {

    @Schema(description = "ID del producto a vender", example = "1")
    @NotNull
    private Integer productoId;

    @Schema(description = "Cantidad a vender", example = "2.0")
    @NotNull
    @Positive
    private Double cantidad;
}
