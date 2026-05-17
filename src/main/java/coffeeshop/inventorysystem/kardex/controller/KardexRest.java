package coffeeshop.inventorysystem.kardex.controller;

import coffeeshop.inventorysystem.kardex.dto.MovimientoRequest;
import coffeeshop.inventorysystem.kardex.dto.VentaRequest;
import coffeeshop.inventorysystem.kardex.model.Kardex;
import coffeeshop.inventorysystem.kardex.service.KardexService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/kardex")
public interface KardexRest {

    @PostMapping(path = "/entrada")
    ResponseEntity<String> registrarEntrada(@Valid @RequestBody MovimientoRequest request);

    @PostMapping(path = "/salida")
    ResponseEntity<String> registrarSalida(@Valid @RequestBody MovimientoRequest request);

    @PostMapping(path = "/vender")
    ResponseEntity<String> vender(@Valid @RequestBody VentaRequest request);

    @GetMapping(path = "/ingrediente/{ingredienteId}")
    ResponseEntity<List<Kardex>> getByIngrediente(@PathVariable Integer ingredienteId);

    @GetMapping(path = "/getAll")
    ResponseEntity<List<Kardex>> getAll();
}
