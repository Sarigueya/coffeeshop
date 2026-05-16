package coffeeshop.inventorysystem.ingrediente.service;

import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import coffeeshop.inventorysystem.ingrediente.model.UnidadMedida;
import coffeeshop.inventorysystem.ingrediente.repository.IngredienteDao;
import coffeeshop.inventorysystem.ingrediente.repository.UnidadMedidaDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredienteServiceImpl implements IngredienteService {

    private final IngredienteDao ingredienteDao;

    private final UnidadMedidaDao unidadMedidaDao;

    @Override
    public ResponseEntity<String> create(Map<String, String> requestMap) {
        try {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setNombre(requestMap.get("nombre"));
            ingrediente.setCostoUnitario(Double.parseDouble(requestMap.get("costoUnitario")));
            ingrediente.setActivo(true);
            ingrediente.setStockMinimo(Double.parseDouble(requestMap.get("stockMinimo")));

            if (requestMap.containsKey("unidadMedidaId")) {
                Optional<UnidadMedida> um = unidadMedidaDao.findById(Integer.parseInt(requestMap.get("unidadMedidaId")));
                um.ifPresent(ingrediente::setUnidadMedida);
            }

            ingredienteDao.save(ingrediente);
            return CafeUtils.getResponseEntity("Ingrediente creado exitosamente.", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            Optional<Ingrediente> optional = ingredienteDao.findById(Integer.parseInt(requestMap.get("id")));
            if (optional.isPresent()) {
                Ingrediente ingrediente = optional.get();
                if (requestMap.containsKey("nombre")) ingrediente.setNombre(requestMap.get("nombre"));
                if (requestMap.containsKey("costoUnitario")) ingrediente.setCostoUnitario(Double.parseDouble(requestMap.get("costoUnitario")));
                if (requestMap.containsKey("activo")) ingrediente.setActivo(Boolean.parseBoolean(requestMap.get("activo")));
                if (requestMap.containsKey("stockMinimo")) ingrediente.setStockMinimo(Double.parseDouble(requestMap.get("stockMinimo")));
                if (requestMap.containsKey("unidadMedidaId")) {
                    Optional<UnidadMedida> um = unidadMedidaDao.findById(Integer.parseInt(requestMap.get("unidadMedidaId")));
                    um.ifPresent(ingrediente::setUnidadMedida);
                }
                ingredienteDao.save(ingrediente);
                return CafeUtils.getResponseEntity("Ingrediente actualizado exitosamente.", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity("Ingrediente no encontrado.", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        try {
            if (ingredienteDao.existsById(id)) {
                ingredienteDao.deleteById(id);
                return CafeUtils.getResponseEntity("Ingrediente eliminado exitosamente.", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity("Ingrediente no encontrado.", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Ingrediente> getById(Integer id) {
        try {
            Optional<Ingrediente> optional = ingredienteDao.findById(id);
            if (optional.isPresent()) {
                return new ResponseEntity<>(optional.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Ingrediente(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Ingrediente(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Ingrediente>> getAll() {
        try {
            return new ResponseEntity<>(ingredienteDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
