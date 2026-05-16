package coffeeshop.inventorysystem.ingrediente.repository;

import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteDao extends JpaRepository<Ingrediente, Integer> {
}
