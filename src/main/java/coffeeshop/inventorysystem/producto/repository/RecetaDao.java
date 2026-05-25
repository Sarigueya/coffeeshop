package coffeeshop.inventorysystem.producto.repository;

import coffeeshop.inventorysystem.producto.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaDao extends JpaRepository<Receta, Integer> {
}
