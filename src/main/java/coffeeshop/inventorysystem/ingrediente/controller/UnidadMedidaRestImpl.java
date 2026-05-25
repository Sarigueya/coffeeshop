package coffeeshop.inventorysystem.ingrediente.controller;

import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.ingrediente.dto.UnidadMedidaRequest;
import coffeeshop.inventorysystem.ingrediente.model.UnidadMedida;
import coffeeshop.inventorysystem.ingrediente.service.UnidadMedidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del controlador REST de unidades de medida.
 * <p>
 * Expone los endpoints definidos en {@link UnidadMedidaRest} y delega
 * la lógica de negocio a {@link UnidadMedidaService}.
 * </p>
 *
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
public class UnidadMedidaRestImpl implements UnidadMedidaRest {

    private final UnidadMedidaService unidadMedidaService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> create(UnidadMedidaRequest request) {
        try {
            return unidadMedidaService.create(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(UnidadMedidaRequest request) {
        try {
            return unidadMedidaService.update(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(Integer id) {
        try {
            return unidadMedidaService.delete(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UnidadMedida> getById(Integer id) {
        try {
            return unidadMedidaService.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new UnidadMedida(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UnidadMedida>> getAll() {
        try {
            return unidadMedidaService.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
