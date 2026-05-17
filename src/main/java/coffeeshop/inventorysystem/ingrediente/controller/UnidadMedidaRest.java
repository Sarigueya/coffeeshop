package coffeeshop.inventorysystem.ingrediente.controller;

import coffeeshop.inventorysystem.ingrediente.dto.UnidadMedidaRequest;
import coffeeshop.inventorysystem.ingrediente.model.UnidadMedida;
import coffeeshop.inventorysystem.ingrediente.service.UnidadMedidaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/unidad-medida")
public interface UnidadMedidaRest {

    @PostMapping(path = "/create")
    ResponseEntity<String> create(@Valid @RequestBody UnidadMedidaRequest request);

    @PutMapping(path = "/update")
    ResponseEntity<String> update(@Valid @RequestBody UnidadMedidaRequest request);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping(path = "/get/{id}")
    ResponseEntity<UnidadMedida> getById(@PathVariable Integer id);

    @GetMapping(path = "/getAll")
    ResponseEntity<List<UnidadMedida>> getAll();
}
