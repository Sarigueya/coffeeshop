package coffeeshop.inventorysystem.producto.controller;

import coffeeshop.inventorysystem.producto.model.Producto;
import coffeeshop.inventorysystem.producto.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/producto")
public interface ProductoRest {

    @PostMapping(path = "/create")
    ResponseEntity<String> create(@RequestBody Map<String, String> requestMap);

    @PutMapping(path = "/update")
    ResponseEntity<String> update(@RequestBody Map<String, String> requestMap);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Producto> getById(@PathVariable Integer id);

    @GetMapping(path = "/getAll")
    ResponseEntity<List<Producto>> getAll();
}
