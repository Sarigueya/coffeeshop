package coffeeshop.inventorysystem.kardex.repository;

import coffeeshop.inventorysystem.kardex.model.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio JPA para la entidad {@link Kardex}.
 *
 * @since 1.0
 */
public interface KardexDao extends JpaRepository<Kardex, Integer> {

    /**
     * Obtiene todos los movimientos de kardex de un ingrediente.
     *
     * @param ingredienteId ID del ingrediente
     * @return lista de movimientos ordenados por fecha
     */
    List<Kardex> findByIngredienteId(Integer ingredienteId);
}
