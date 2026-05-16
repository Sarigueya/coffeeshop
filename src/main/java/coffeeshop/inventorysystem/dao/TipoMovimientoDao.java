package coffeeshop.inventorysystem.dao;

import coffeeshop.inventorysystem.POJO.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoMovimientoDao extends JpaRepository<TipoMovimiento, Integer> {
}
