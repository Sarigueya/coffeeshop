package coffeeshop.inventorysystem.producto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProductoRequest {

    private Integer id;

    @NotBlank
    private String nombre;

    private String descripcion;

    @NotNull
    private Double precioVenta;

    private Boolean activo;

    private List<RecetaDetalleRequest> recetaDetalles;
}
