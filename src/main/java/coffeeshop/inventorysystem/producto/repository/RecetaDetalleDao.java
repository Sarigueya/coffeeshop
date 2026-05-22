package coffeeshop.inventorysystem.producto.repository;

import coffeeshop.inventorysystem.producto.model.RecetaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecetaDetalleDao extends JpaRepository<RecetaDetalle, Integer> {
    List<RecetaDetalle> findByRecetaId(Integer recetaId);

    void deleteByRecetaId(Integer recetaId);
}
