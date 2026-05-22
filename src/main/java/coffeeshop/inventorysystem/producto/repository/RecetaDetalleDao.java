package coffeeshop.inventorysystem.producto.repository;

import coffeeshop.inventorysystem.producto.model.RecetaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio JPA para la entidad {@link RecetaDetalle}.
 *
 * @since 1.0
 */
public interface RecetaDetalleDao extends JpaRepository<RecetaDetalle, Integer> {

    /**
     * Obtiene todos los detalles de una receta.
     *
     * @param recetaId ID de la receta
     * @return lista de detalles de la receta
     */
    List<RecetaDetalle> findByRecetaId(Integer recetaId);

    /**
     * Elimina todos los detalles de una receta.
     *
     * @param recetaId ID de la receta cuyos detalles se eliminarán
     */
    void deleteByRecetaId(Integer recetaId);
}
