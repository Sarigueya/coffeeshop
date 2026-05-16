package coffeeshop.inventorysystem.ingrediente.controller;

import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import coffeeshop.inventorysystem.ingrediente.service.IngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/ingrediente")
public interface IngredienteRest {

    @PostMapping(path = "/create")
    ResponseEntity<String> create(@RequestBody Map<String, String> requestMap);

    @PutMapping(path = "/update")
    ResponseEntity<String> update(@RequestBody Map<String, String> requestMap);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Ingrediente> getById(@PathVariable Integer id);

    @GetMapping(path = "/getAll")
    ResponseEntity<List<Ingrediente>> getAll();
}
