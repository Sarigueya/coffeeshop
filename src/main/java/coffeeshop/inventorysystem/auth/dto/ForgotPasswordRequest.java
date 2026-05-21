package coffeeshop.inventorysystem.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Solicitud de recuperación de contraseña")
public class ForgotPasswordRequest {

    @Schema(description = "Correo electrónico del usuario", example = "usuario@cafeteria.com")
    @NotBlank
    @Email
    private String email;
}
