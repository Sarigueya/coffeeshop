package coffeeshop.inventorysystem.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @NotNull
    private Integer id;

    @NotBlank
    private String status;
}
