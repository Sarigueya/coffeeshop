package coffeeshop.inventorysystem.producto.service;

import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
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
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDao productoDao;

    private final RecetaDao recetaDao;

    @Override
    public ResponseEntity<String> create(Map<String, String> requestMap) {
        try {
            Producto producto = new Producto();
            producto.setNombre(requestMap.get("nombre"));
            producto.setDescripcion(requestMap.get("descripcion"));
            producto.setPrecioVenta(Double.parseDouble(requestMap.get("precioVenta")));
            producto.setActivo(true);

            if (requestMap.containsKey("recetaId")) {
                Optional<Receta> receta = recetaDao.findById(Integer.parseInt(requestMap.get("recetaId")));
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
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            Optional<Producto> optional = productoDao.findById(Integer.parseInt(requestMap.get("id")));
            if (optional.isPresent()) {
                Producto producto = optional.get();
                if (requestMap.containsKey("nombre")) producto.setNombre(requestMap.get("nombre"));
                if (requestMap.containsKey("descripcion")) producto.setDescripcion(requestMap.get("descripcion"));
                if (requestMap.containsKey("precioVenta")) producto.setPrecioVenta(Double.parseDouble(requestMap.get("precioVenta")));
                if (requestMap.containsKey("activo")) producto.setActivo(Boolean.parseBoolean(requestMap.get("activo")));
                if (requestMap.containsKey("recetaId")) {
                    Optional<Receta> receta = recetaDao.findById(Integer.parseInt(requestMap.get("recetaId")));
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
