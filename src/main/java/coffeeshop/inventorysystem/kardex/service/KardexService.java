package coffeeshop.inventorysystem.kardex.service;

import coffeeshop.inventorysystem.kardex.dto.KardexSaldoResponse;
import coffeeshop.inventorysystem.kardex.dto.MovimientoRequest;
import coffeeshop.inventorysystem.kardex.dto.VentaRequest;
import coffeeshop.inventorysystem.kardex.model.Kardex;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KardexService {

    ResponseEntity<String> registrarEntrada(MovimientoRequest request);

    ResponseEntity<String> registrarSalida(MovimientoRequest request);

    ResponseEntity<String> vender(VentaRequest request);

    ResponseEntity<List<Kardex>> getByIngrediente(Integer ingredienteId);

    ResponseEntity<List<Kardex>> getAll();

    ResponseEntity<List<KardexSaldoResponse>> getSaldos();
}
