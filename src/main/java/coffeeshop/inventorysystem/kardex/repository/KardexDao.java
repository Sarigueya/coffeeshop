package coffeeshop.inventorysystem.kardex.repository;

import coffeeshop.inventorysystem.kardex.model.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KardexDao extends JpaRepository<Kardex, Integer> {
    List<Kardex> findByIngredienteId(Integer ingredienteId);
}
