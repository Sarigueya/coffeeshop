package coffeeshop.inventorysystem.ingrediente.repository;

import coffeeshop.inventorysystem.ingrediente.model.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadMedidaDao extends JpaRepository<UnidadMedida, Integer> {
}
