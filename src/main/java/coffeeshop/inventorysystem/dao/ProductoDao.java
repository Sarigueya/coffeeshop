package coffeeshop.inventorysystem.dao;

import coffeeshop.inventorysystem.POJO.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto, Integer> {
}
