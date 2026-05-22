package coffeeshop.inventorysystem.ingrediente.service;

import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.ingrediente.dto.UnidadMedidaRequest;
import coffeeshop.inventorysystem.ingrediente.model.UnidadMedida;
import coffeeshop.inventorysystem.ingrediente.repository.UnidadMedidaDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de {@link UnidadMedidaService}.
 *
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    private final UnidadMedidaDao unidadMedidaDao;

    private final MessageSource messageSource;

    @Override
    public ResponseEntity<String> create(UnidadMedidaRequest request) {
        try {
            UnidadMedida um = new UnidadMedida();
            um.setNombreUnidad(request.getNombreUnidad());
            unidadMedidaDao.save(um);
            return CafeUtils.getResponseEntity(
                    messageSource.getMessage("unidad.created", null, LocaleContextHolder.getLocale()),
                    HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(UnidadMedidaRequest request) {
        try {
            Optional<UnidadMedida> optional = unidadMedidaDao.findById(request.getId());
            if (optional.isPresent()) {
                UnidadMedida um = optional.get();
                if (request.getNombreUnidad() != null) {
                    um.setNombreUnidad(request.getNombreUnidad());
                }
                unidadMedidaDao.save(um);
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("unidad.updated", null, LocaleContextHolder.getLocale()),
                        HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity(
                    messageSource.getMessage("unidad.not.found", null, LocaleContextHolder.getLocale()),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        try {
            if (unidadMedidaDao.existsById(id)) {
                unidadMedidaDao.deleteById(id);
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("unidad.deleted", null, LocaleContextHolder.getLocale()),
                        HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity(
                    messageSource.getMessage("unidad.not.found", null, LocaleContextHolder.getLocale()),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UnidadMedida> getById(Integer id) {
        try {
            Optional<UnidadMedida> optional = unidadMedidaDao.findById(id);
            if (optional.isPresent()) {
                return new ResponseEntity<>(optional.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new UnidadMedida(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new UnidadMedida(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UnidadMedida>> getAll() {
        try {
            return new ResponseEntity<>(unidadMedidaDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
