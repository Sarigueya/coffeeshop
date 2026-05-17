package coffeeshop.inventorysystem.kardex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KardexSaldoResponse {

    private Integer ingredienteId;

    private String ingredienteNombre;

    private Double saldoActual;

}
