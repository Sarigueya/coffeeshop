package coffeeshop.inventorysystem.ingrediente.repository;

import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad {@link Ingrediente}.
 *
 * @since 1.0
 */
public interface IngredienteDao extends JpaRepository<Ingrediente, Integer> {

    /**
     * Verifica si existe un ingrediente con el nombre dado.
     *
     * @param nombre nombre a buscar
     * @return {@code true} si ya existe un ingrediente con ese nombre
     */
    boolean existsByNombre(String nombre);

    /**
     * Verifica si existe un ingrediente con el nombre dado,
     * excluyendo un ID específico (para validación en actualizaciones).
     *
     * @param nombre nombre a buscar
     * @param id     ID del ingrediente a excluir
     * @return {@code true} si existe otro ingrediente con ese nombre
     */
    boolean existsByNombreAndIdNot(String nombre, Integer id);
}
