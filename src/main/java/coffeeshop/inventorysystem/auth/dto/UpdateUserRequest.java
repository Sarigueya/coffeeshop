package coffeeshop.inventorysystem.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO que encapsula los datos para actualizar el estado de un usuario.
 * <p>
 * Permite activar ({@code "true"}) o desactivar ({@code "false"})
 * la cuenta de un usuario. Requiere rol ADMIN.
 * </p>
 *
 * @since 1.0
 */
@Data
@Schema(description = "Solicitud de actualización de estado de usuario")
public class UpdateUserRequest {

    @Schema(description = "ID del usuario a actualizar", example = "1")
    @NotNull
    private Integer id;

    @Schema(description = "Nuevo estado del usuario (true/false)", example = "true")
    @NotBlank
    private String status;
}
