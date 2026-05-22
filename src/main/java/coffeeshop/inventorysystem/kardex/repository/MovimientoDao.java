package coffeeshop.inventorysystem.kardex.repository;

import coffeeshop.inventorysystem.kardex.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Movimiento}.
 *
 * @since 1.0
 */
public interface MovimientoDao extends JpaRepository<Movimiento, Integer> {

    /**
     * Busca un tipo de movimiento por su nombre.
     *
     * @param nombreMovimiento nombre del movimiento (ej: "Entrada", "Salida", "Venta")
     * @return el movimiento si se encuentra, o vacío si no existe
     */
    Optional<Movimiento> findByNombreMovimiento(String nombreMovimiento);
}
