package coffeeshop.inventorysystem.producto.controller;

import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.producto.dto.ProductoRequest;
import coffeeshop.inventorysystem.producto.model.Producto;
import coffeeshop.inventorysystem.producto.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del controlador REST de productos.
 * <p>
 * Expone los endpoints definidos en {@link ProductoRest} y delega
 * la lógica de negocio a {@link ProductoService}.
 * </p>
 *
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
public class ProductoRestImpl implements ProductoRest {

    private final ProductoService productoService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> create(ProductoRequest request) {
        try {
            return productoService.create(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(ProductoRequest request) {
        try {
            return productoService.update(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(Integer id) {
        try {
            return productoService.delete(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Producto> getById(Integer id) {
        try {
            return productoService.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Producto(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Producto>> getAll() {
        try {
            return productoService.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
