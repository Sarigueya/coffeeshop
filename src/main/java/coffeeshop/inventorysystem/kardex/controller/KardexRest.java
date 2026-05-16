package coffeeshop.inventorysystem.kardex.controller;

import coffeeshop.inventorysystem.kardex.model.Kardex;
import coffeeshop.inventorysystem.kardex.service.KardexService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/kardex")
public interface KardexRest {

    @PostMapping(path = "/entrada")
    ResponseEntity<String> registrarEntrada(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/salida")
    ResponseEntity<String> registrarSalida(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/ingrediente/{ingredienteId}")
    ResponseEntity<List<Kardex>> getByIngrediente(@PathVariable Integer ingredienteId);

    @GetMapping(path = "/getAll")
    ResponseEntity<List<Kardex>> getAll();
}
