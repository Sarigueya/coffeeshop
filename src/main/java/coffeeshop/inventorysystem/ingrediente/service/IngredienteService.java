package coffeeshop.inventorysystem.ingrediente.service;

import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IngredienteService {

    ResponseEntity<String> create(Map<String, String> requestMap);

    ResponseEntity<String> update(Map<String, String> requestMap);

    ResponseEntity<String> delete(Integer id);

    ResponseEntity<Ingrediente> getById(Integer id);

    ResponseEntity<List<Ingrediente>> getAll();
}
