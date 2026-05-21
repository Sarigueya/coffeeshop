package coffeeshop.inventorysystem.producto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Solicitud de creación o actualización de producto")
public class ProductoRequest {

    @Schema(description = "ID del producto (solo para actualización)", example = "1")
    private Integer id;

    @Schema(description = "Nombre del producto", example = "Café Latte")
    @NotBlank
    private String nombre;

    @Schema(description = "Descripción del producto", example = "Café espresso con leche vaporizada")
    private String descripcion;

    @Schema(description = "Precio de venta", example = "45.00")
    @NotNull
    private Double precioVenta;

    @Schema(description = "Indica si el producto está activo", example = "true")
    private Boolean activo;

    @Schema(description = "Lista de ingredientes con cantidades para la receta")
    private List<RecetaDetalleRequest> recetaDetalles;
}
