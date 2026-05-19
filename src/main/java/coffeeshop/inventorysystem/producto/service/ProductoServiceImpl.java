package coffeeshop.inventorysystem.producto.service;

import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import coffeeshop.inventorysystem.ingrediente.repository.IngredienteDao;
import coffeeshop.inventorysystem.producto.dto.ProductoRequest;
import coffeeshop.inventorysystem.producto.dto.RecetaDetalleRequest;
import coffeeshop.inventorysystem.producto.model.Producto;
import coffeeshop.inventorysystem.producto.model.Receta;
import coffeeshop.inventorysystem.producto.model.RecetaDetalle;
import coffeeshop.inventorysystem.producto.repository.ProductoDao;
import coffeeshop.inventorysystem.producto.repository.RecetaDao;
import coffeeshop.inventorysystem.producto.repository.RecetaDetalleDao;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDao productoDao;

    private final RecetaDao recetaDao;

    private final RecetaDetalleDao recetaDetalleDao;

    private final IngredienteDao ingredienteDao;

    private final MessageSource messageSource;

    @Override
    public ResponseEntity<String> create(ProductoRequest request) {
        try {
            Producto producto = new Producto();
            producto.setNombre(request.getNombre());
            producto.setDescripcion(request.getDescripcion());
            producto.setPrecioVenta(request.getPrecioVenta());
            producto.setActivo(true);

            if (request.getRecetaDetalles() != null && !request.getRecetaDetalles().isEmpty()) {
                List<Integer> idsNoExistentes = request.getRecetaDetalles().stream()
                        .map(RecetaDetalleRequest::getIngredienteId)
                        .filter(id -> !ingredienteDao.existsById(id))
                        .toList();

                if (!idsNoExistentes.isEmpty()) {
                    return CafeUtils.getResponseEntity(
                            messageSource.getMessage("producto.ingredients.not.found",
                                    new Object[]{idsNoExistentes.toString()}, LocaleContextHolder.getLocale()),
                            HttpStatus.BAD_REQUEST
                    );
                }

                Receta receta = new Receta();
                receta.setNombre(request.getNombre());
                receta.setDescripcion(messageSource.getMessage("producto.recipe.description",
                        new Object[]{request.getNombre()}, LocaleContextHolder.getLocale()));
                receta.setActivo(true);
                receta = recetaDao.save(receta);

                for (RecetaDetalleRequest detalleReq : request.getRecetaDetalles()) {
                    Ingrediente ingrediente = ingredienteDao.findById(detalleReq.getIngredienteId()).get();
                    RecetaDetalle detalle = new RecetaDetalle();
                    detalle.setReceta(receta);
                    detalle.setIngrediente(ingrediente);
                    detalle.setCantidadRequerida(detalleReq.getCantidadRequerida());
                    recetaDetalleDao.save(detalle);
                }

                producto.setReceta(receta);
            }

            productoDao.save(producto);
            return CafeUtils.getResponseEntity(
                    messageSource.getMessage("producto.created", null, LocaleContextHolder.getLocale()),
                    HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(ProductoRequest request) {
        try {
            Optional<Producto> optional = productoDao.findById(request.getId());
            if (optional.isPresent()) {
                Producto producto = optional.get();
                if (request.getNombre() != null) producto.setNombre(request.getNombre());
                if (request.getDescripcion() != null) producto.setDescripcion(request.getDescripcion());
                if (request.getPrecioVenta() != null) producto.setPrecioVenta(request.getPrecioVenta());
                if (request.getActivo() != null) producto.setActivo(request.getActivo());

                if (request.getRecetaDetalles() != null) {
                    if (!request.getRecetaDetalles().isEmpty()) {
                        List<Integer> idsNoExistentes = request.getRecetaDetalles().stream()
                                .map(RecetaDetalleRequest::getIngredienteId)
                                .filter(id -> !ingredienteDao.existsById(id))
                                .toList();

                        if (!idsNoExistentes.isEmpty()) {
                            return CafeUtils.getResponseEntity(
                                    messageSource.getMessage("producto.ingredients.not.found",
                                            new Object[]{idsNoExistentes.toString()}, LocaleContextHolder.getLocale()),
                                    HttpStatus.BAD_REQUEST
                            );
                        }
                    }

                    Receta receta = producto.getReceta();
                    if (receta == null) {
                        receta = new Receta();
                        String nombre = request.getNombre() != null ? request.getNombre() : producto.getNombre();
                        receta.setNombre(nombre);
                        receta.setDescripcion(messageSource.getMessage("producto.recipe.description",
                                new Object[]{nombre}, LocaleContextHolder.getLocale()));
                        receta.setActivo(true);
                        receta = recetaDao.save(receta);
                    } else {
                        recetaDetalleDao.findByRecetaId(receta.getId())
                                .forEach(rd -> recetaDetalleDao.delete(rd));
                    }

                    for (RecetaDetalleRequest detalleReq : request.getRecetaDetalles()) {
                        Ingrediente ingrediente = ingredienteDao.findById(detalleReq.getIngredienteId()).get();
                        RecetaDetalle detalle = new RecetaDetalle();
                        detalle.setReceta(receta);
                        detalle.setIngrediente(ingrediente);
                        detalle.setCantidadRequerida(detalleReq.getCantidadRequerida());
                        recetaDetalleDao.save(detalle);
                    }

                    producto.setReceta(receta);
                }

                productoDao.save(producto);
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("producto.updated", null, LocaleContextHolder.getLocale()),
                        HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity(
                    messageSource.getMessage("producto.not.found", null, LocaleContextHolder.getLocale()),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        try {
            if (productoDao.existsById(id)) {
                productoDao.deleteById(id);
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("producto.deleted", null, LocaleContextHolder.getLocale()),
                        HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity(
                    messageSource.getMessage("producto.not.found", null, LocaleContextHolder.getLocale()),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Producto> getById(Integer id) {
        try {
            Optional<Producto> optional = productoDao.findById(id);
            if (optional.isPresent()) {
                return new ResponseEntity<>(optional.get(), HttpStatus.OK);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Producto(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Producto>> getAll() {
        try {
            return new ResponseEntity<>(productoDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
