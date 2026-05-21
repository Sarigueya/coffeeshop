package coffeeshop.inventorysystem.kardex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Saldo actual de un ingrediente en el inventario")
public class KardexSaldoResponse {

    @Schema(description = "ID del ingrediente", example = "1")
    private Integer ingredienteId;

    @Schema(description = "Nombre del ingrediente", example = "Harina")
    private String ingredienteNombre;

    @Schema(description = "Cantidad actual en stock", example = "100.0")
    private Double saldoActual;

}
