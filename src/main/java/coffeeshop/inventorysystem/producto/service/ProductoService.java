package coffeeshop.inventorysystem.producto.service;

import coffeeshop.inventorysystem.producto.dto.ProductoRequest;
import coffeeshop.inventorysystem.producto.model.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Servicio para la gestión de productos y sus recetas asociadas.
 * <p>
 * Define las operaciones CRUD sobre productos, incluyendo la
 * administración de las recetas con sus ingredientes y cantidades.
 * </p>
 *
 * @since 1.0
 */
public interface ProductoService {

    /**
     * Crea un nuevo producto con su receta opcional.
     *
     * @param request datos del producto y lista de ingredientes para la receta
     * @return {@code 200 OK} si se creó exitosamente,
     *         {@code 400 BAD_REQUEST} si el nombre ya existe o ingrediente no encontrado
     */
    ResponseEntity<String> create(ProductoRequest request);

    /**
     * Actualiza un producto existente y su receta.
     *
     * @param request datos actualizados del producto, incluye {@code id} obligatorio
     * @return {@code 200 OK} si se actualizó exitosamente,
     *         {@code 400 BAD_REQUEST} si el producto no se encuentra o hay duplicados
     */
    ResponseEntity<String> update(ProductoRequest request);

    /**
     * Elimina un producto y su receta asociada.
     *
     * @param id identificador del producto a eliminar
     * @return {@code 200 OK} si se eliminó exitosamente,
     *         {@code 400 BAD_REQUEST} si el producto no existe
     */
    ResponseEntity<String> delete(Integer id);

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id identificador del producto
     * @return el producto con {@code 200 OK}, o {@code 404 NOT_FOUND} si no existe
     */
    ResponseEntity<Producto> getById(Integer id);

    /**
     * Obtiene todos los productos registrados.
     *
     * @return lista de productos
     */
    ResponseEntity<List<Producto>> getAll();
}
