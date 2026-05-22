package coffeeshop.inventorysystem.producto.repository;

import coffeeshop.inventorysystem.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto, Integer> {
    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Integer id);
}
