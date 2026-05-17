package coffeeshop.inventorysystem.ingrediente.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UnidadMedidaRequest {

    private Integer id;

    @NotBlank
    private String nombreUnidad;
}
