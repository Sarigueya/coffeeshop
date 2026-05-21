package coffeeshop.inventorysystem.kardex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

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
