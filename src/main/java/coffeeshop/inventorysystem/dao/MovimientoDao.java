package coffeeshop.inventorysystem.dao;

import coffeeshop.inventorysystem.POJO.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoDao extends JpaRepository<Movimiento, Integer> {
}
