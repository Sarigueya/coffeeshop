package coffeeshop.inventorysystem.kardex.repository;

import coffeeshop.inventorysystem.kardex.model.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoMovimientoDao extends JpaRepository<TipoMovimiento, Integer> {
}
