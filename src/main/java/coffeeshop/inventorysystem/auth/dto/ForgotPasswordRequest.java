package coffeeshop.inventorysystem.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO que encapsula el correo electrónico para recuperar la contraseña.
 * <p>
 * Envía una contraseña temporal al correo especificado si existe
 * en el sistema.
 * </p>
 *
 * @since 1.0
 */
@Data
@Schema(description = "Solicitud de recuperación de contraseña")
public class ForgotPasswordRequest {

    @Schema(description = "Correo electrónico del usuario", example = "usuario@cafeteria.com")
    @NotBlank
    @Email
    private String email;
}
