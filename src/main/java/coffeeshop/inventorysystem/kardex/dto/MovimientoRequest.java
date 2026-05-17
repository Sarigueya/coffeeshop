package coffeeshop.inventorysystem.kardex.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MovimientoRequest {

    @NotNull
    private Integer ingredienteId;

    @NotNull
    private Integer movimientoId;

    @NotNull
    @Positive
    private Double cantidad;
}
