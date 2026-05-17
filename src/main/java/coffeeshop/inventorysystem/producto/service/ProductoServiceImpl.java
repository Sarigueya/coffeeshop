package coffeeshop.inventorysystem.producto.service;

import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.producto.dto.ProductoRequest;
import coffeeshop.inventorysystem.producto.model.Producto;
import coffeeshop.inventorysystem.producto.model.Receta;
import coffeeshop.inventorysystem.producto.repository.ProductoDao;
import coffeeshop.inventorysystem.producto.repository.RecetaDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public ResponseEntity<String> create(ProductoRequest request) {
        try {
            Producto producto = new Producto();
            producto.setNombre(request.getNombre());
            producto.setDescripcion(request.getDescripcion());
            producto.setPrecioVenta(request.getPrecioVenta());
            producto.setActivo(true);

            if (request.getRecetaId() != null) {
                Optional<Receta> receta = recetaDao.findById(request.getRecetaId());
                receta.ifPresent(producto::setReceta);
            }

            productoDao.save(producto);
            return CafeUtils.getResponseEntity("Producto creado exitosamente.", HttpStatus.OK);
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
                if (request.getRecetaId() != null) {
                    Optional<Receta> receta = recetaDao.findById(request.getRecetaId());
                    receta.ifPresent(producto::setReceta);
                }
                productoDao.save(producto);
                return CafeUtils.getResponseEntity("Producto actualizado exitosamente.", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity("Producto no encontrado.", HttpStatus.BAD_REQUEST);
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
                return CafeUtils.getResponseEntity("Producto eliminado exitosamente.", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity("Producto no encontrado.", HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<>(new Producto(), HttpStatus.BAD_REQUEST);
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
