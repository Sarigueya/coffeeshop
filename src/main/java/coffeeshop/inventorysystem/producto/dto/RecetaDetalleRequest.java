package coffeeshop.inventorysystem.producto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Detalle de ingrediente requerido en una receta")
public class RecetaDetalleRequest {

    @Schema(description = "ID del ingrediente", example = "1")
    @NotNull
    private Integer ingredienteId;

    @Schema(description = "Cantidad requerida del ingrediente", example = "0.5")
    @NotNull
    private Double cantidadRequerida;
}
