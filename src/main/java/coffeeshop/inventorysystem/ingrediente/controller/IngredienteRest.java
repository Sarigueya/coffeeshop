package coffeeshop.inventorysystem.ingrediente.controller;

import coffeeshop.inventorysystem.ingrediente.dto.IngredienteRequest;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import coffeeshop.inventorysystem.ingrediente.service.IngredienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/ingrediente")
public interface IngredienteRest {

    @PostMapping(path = "/create")
    ResponseEntity<String> create(@Valid @RequestBody IngredienteRequest request);

    @PutMapping(path = "/update")
    ResponseEntity<String> update(@Valid @RequestBody IngredienteRequest request);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Ingrediente> getById(@PathVariable Integer id);

    @GetMapping(path = "/getAll")
    ResponseEntity<List<Ingrediente>> getAll();
}
