package coffeeshop.inventorysystem.dao;

import coffeeshop.inventorysystem.POJO.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaDao extends JpaRepository<Receta, Integer> {
}
