package coffeeshop.inventorysystem.producto.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecetaDetalleRequest {

    @NotNull
    private Integer ingredienteId;

    @NotNull
    private Double cantidadRequerida;
}
