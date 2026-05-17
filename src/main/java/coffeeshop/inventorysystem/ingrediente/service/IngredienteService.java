package coffeeshop.inventorysystem.ingrediente.service;

import coffeeshop.inventorysystem.ingrediente.dto.IngredienteRequest;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IngredienteService {

    ResponseEntity<String> create(IngredienteRequest request);

    ResponseEntity<String> update(IngredienteRequest request);

    ResponseEntity<String> delete(Integer id);

    ResponseEntity<Ingrediente> getById(Integer id);

    ResponseEntity<List<Ingrediente>> getAll();
}
