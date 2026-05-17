package coffeeshop.inventorysystem.ingrediente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IngredienteRequest {

    private Integer id;

    @NotBlank
    private String nombre;

    @NotNull
    private Double costoUnitario;

    private Boolean activo;

    @NotNull
    private Double stockMinimo;

    private Integer unidadMedidaId;
}
