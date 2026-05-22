package coffeeshop.inventorysystem.producto.repository;

import coffeeshop.inventorysystem.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad {@link Producto}.
 *
 * @since 1.0
 */
public interface ProductoDao extends JpaRepository<Producto, Integer> {

    /**
     * Verifica si existe un producto con el nombre dado.
     *
     * @param nombre nombre a buscar
     * @return {@code true} si ya existe un producto con ese nombre
     */
    boolean existsByNombre(String nombre);

    /**
     * Verifica si existe un producto con el nombre dado,
     * excluyendo un ID específico (para validación en actualizaciones).
     *
     * @param nombre nombre a buscar
     * @param id     ID del producto a excluir
     * @return {@code true} si existe otro producto con ese nombre
     */
    boolean existsByNombreAndIdNot(String nombre, Integer id);
}
