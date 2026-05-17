package coffeeshop.inventorysystem.producto.service;

import coffeeshop.inventorysystem.producto.dto.ProductoRequest;
import coffeeshop.inventorysystem.producto.model.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {

    ResponseEntity<String> create(ProductoRequest request);

    ResponseEntity<String> update(ProductoRequest request);

    ResponseEntity<String> delete(Integer id);

    ResponseEntity<Producto> getById(Integer id);

    ResponseEntity<List<Producto>> getAll();
}
