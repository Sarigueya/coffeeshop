package coffeeshop.inventorysystem.kardex.service;

import coffeeshop.inventorysystem.kardex.model.Kardex;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface KardexService {

    ResponseEntity<String> registrarEntrada(Map<String, String> requestMap);

    ResponseEntity<String> registrarSalida(Map<String, String> requestMap);

    ResponseEntity<List<Kardex>> getByIngrediente(Integer ingredienteId);

    ResponseEntity<List<Kardex>> getAll();
}
