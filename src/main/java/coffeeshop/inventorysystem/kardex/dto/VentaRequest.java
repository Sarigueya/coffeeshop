package coffeeshop.inventorysystem.kardex.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class VentaRequest {

    @NotNull
    private Integer productoId;

    @NotNull
    @Positive
    private Double cantidad;
}
