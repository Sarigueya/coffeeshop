package coffeeshop.inventorysystem.ingrediente.service;

import coffeeshop.inventorysystem.ingrediente.dto.UnidadMedidaRequest;
import coffeeshop.inventorysystem.ingrediente.model.UnidadMedida;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UnidadMedidaService {

    ResponseEntity<String> create(UnidadMedidaRequest request);

    ResponseEntity<String> update(UnidadMedidaRequest request);

    ResponseEntity<String> delete(Integer id);

    ResponseEntity<UnidadMedida> getById(Integer id);

    ResponseEntity<List<UnidadMedida>> getAll();
}
