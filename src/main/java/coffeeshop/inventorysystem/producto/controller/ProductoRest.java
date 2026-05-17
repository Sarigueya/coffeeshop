package coffeeshop.inventorysystem.producto.controller;

import coffeeshop.inventorysystem.producto.dto.ProductoRequest;
import coffeeshop.inventorysystem.producto.model.Producto;
import coffeeshop.inventorysystem.producto.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/producto")
public interface ProductoRest {

    @PostMapping(path = "/create")
    ResponseEntity<String> create(@Valid @RequestBody ProductoRequest request);

    @PutMapping(path = "/update")
    ResponseEntity<String> update(@Valid @RequestBody ProductoRequest request);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Producto> getById(@PathVariable Integer id);

    @GetMapping(path = "/getAll")
    ResponseEntity<List<Producto>> getAll();
}
