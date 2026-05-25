package coffeeshop.inventorysystem.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO que encapsula las credenciales para iniciar sesión.
 *
 * @since 1.0
 */
@Data
@Schema(description = "Solicitud de inicio de sesión")
public class LoginRequest {

    @Schema(description = "Correo electrónico del usuario", example = "admin@cafeteria.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Contraseña del usuario", example = "123456")
    @NotBlank
    private String password;
}
