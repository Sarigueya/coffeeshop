package coffeeshop.inventorysystem.dao;

import coffeeshop.inventorysystem.POJO.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KardexDao extends JpaRepository<Kardex, Integer> {
}
