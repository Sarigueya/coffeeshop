package coffeeshop.inventorysystem.dao;

import coffeeshop.inventorysystem.POJO.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteDao extends JpaRepository<Ingrediente, Integer> {
}
