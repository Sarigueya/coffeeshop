package coffeeshop.inventorysystem.ingrediente.controller;

import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.ingrediente.dto.IngredienteRequest;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import coffeeshop.inventorysystem.ingrediente.service.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IngredienteRestImpl implements IngredienteRest {

    private final IngredienteService ingredienteService;

    @Override
    public ResponseEntity<String> create(IngredienteRequest request) {
        try {
            return ingredienteService.create(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(IngredienteRequest request) {
        try {
            return ingredienteService.update(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        try {
            return ingredienteService.delete(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Ingrediente> getById(Integer id) {
        try {
            return ingredienteService.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Ingrediente(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Ingrediente>> getAll() {
        try {
            return ingredienteService.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
