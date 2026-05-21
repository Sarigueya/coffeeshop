package coffeeshop.inventorysystem.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Solicitud de registro de nuevo usuario")
public class SignupRequest {

    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    @NotBlank
    private String name;

    @Schema(description = "Número de contacto", example = "555-1234")
    @NotBlank
    private String contactNumber;

    @Schema(description = "Correo electrónico", example = "usuario@cafeteria.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Contraseña", example = "miPassword123")
    @NotBlank
    private String password;
}
