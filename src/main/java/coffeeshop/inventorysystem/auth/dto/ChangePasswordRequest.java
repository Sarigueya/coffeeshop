package coffeeshop.inventorysystem.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Solicitud de cambio de contraseña")
public class ChangePasswordRequest {

    @Schema(description = "Contraseña actual", example = "miPassword123")
    @NotBlank
    private String oldPassword;

    @Schema(description = "Nueva contraseña", example = "nuevaPassword456")
    @NotBlank
    private String newPassword;
}
