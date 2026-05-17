package coffeeshop.inventorysystem.kardex.controller;

import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.kardex.dto.MovimientoRequest;
import coffeeshop.inventorysystem.kardex.dto.VentaRequest;
import coffeeshop.inventorysystem.kardex.model.Kardex;
import coffeeshop.inventorysystem.kardex.service.KardexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class KardexRestImpl implements KardexRest {

    private final KardexService kardexService;

    @Override
    public ResponseEntity<String> registrarEntrada(MovimientoRequest request) {
        try {
            return kardexService.registrarEntrada(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> registrarSalida(MovimientoRequest request) {
        try {
            return kardexService.registrarSalida(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> vender(VentaRequest request) {
        try {
            return kardexService.vender(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Kardex>> getByIngrediente(Integer ingredienteId) {
        try {
            return kardexService.getByIngrediente(ingredienteId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Kardex>> getAll() {
        try {
            return kardexService.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
