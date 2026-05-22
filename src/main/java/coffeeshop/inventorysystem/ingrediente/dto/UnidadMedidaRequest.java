package coffeeshop.inventorysystem.ingrediente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO que encapsula los datos para crear o actualizar una unidad de medida.
 * <p>
 * El campo {@code id} se usa solo para actualizaciones.
 * </p>
 *
 * @since 1.0
 */
@Data
@Schema(description = "Solicitud de creación o actualización de unidad de medida")
public class UnidadMedidaRequest {

    @Schema(description = "ID de la unidad (solo para actualización)", example = "1")
    private Integer id;

    @Schema(description = "Nombre de la unidad de medida", example = "g")
    @NotBlank
    private String nombreUnidad;
}
