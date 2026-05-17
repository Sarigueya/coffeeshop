package coffeeshop.inventorysystem.kardex.repository;

import coffeeshop.inventorysystem.kardex.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovimientoDao extends JpaRepository<Movimiento, Integer> {
    Optional<Movimiento> findByNombreMovimiento(String nombreMovimiento);
}
