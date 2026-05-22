package coffeeshop.inventorysystem.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa una vista resumida de un usuario.
 * <p>
 * Se usa para listar usuarios sin exponer datos sensibles
 * como la contraseña. Su constructor es utilizado por la
 * {@code @NamedQuery} en {@link coffeeshop.inventorysystem.auth.model.User}.
 * </p>
 *
 * @since 1.0
 */
@Data
@NoArgsConstructor
@Schema(description = "Información resumida de un usuario")
public class UserWrapper {

    @Schema(description = "ID del usuario", example = "1")
    private Integer id;

    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    private String name;

    @Schema(description = "Correo electrónico", example = "usuario@cafeteria.com")
    private String email;

    @Schema(description = "Número de contacto", example = "555-1234")
    private String contactNumber;

    @Schema(description = "Estado del usuario (true/false)", example = "true")
    private String status;

    public UserWrapper(Integer id, String name, String email, String contactNumber, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;
    }
}
