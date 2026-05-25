package coffeeshop.inventorysystem.ingrediente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO que encapsula los datos para crear o actualizar un ingrediente.
 * <p>
 * El campo {@code id} se usa solo para actualizaciones. El campo
 * {@code unidadMedidaId} es opcional y referencia una unidad de medida existente.
 * </p>
 *
 * @since 1.0
 */
@Data
@Schema(description = "Solicitud de creación o actualización de ingrediente")
public class IngredienteRequest {

    @Schema(description = "ID del ingrediente (solo para actualización)", example = "1")
    private Integer id;

    @Schema(description = "Nombre del ingrediente", example = "Harina")
    @NotBlank
    private String nombre;

    @Schema(description = "Costo por unidad", example = "2.50")
    @NotNull
    private Double costoUnitario;

    @Schema(description = "Indica si el ingrediente está activo", example = "true")
    private Boolean activo;

    @Schema(description = "Stock mínimo para alertas", example = "10.0")
    @NotNull
    private Double stockMinimo;

    @Schema(description = "ID de la unidad de medida", example = "1")
    private Integer unidadMedidaId;
}
