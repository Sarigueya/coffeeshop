package coffeeshop.inventorysystem.kardex.repository;

import coffeeshop.inventorysystem.kardex.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoDao extends JpaRepository<Movimiento, Integer> {
}
