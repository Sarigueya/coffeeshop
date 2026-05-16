package coffeeshop.inventorysystem.dao;

import coffeeshop.inventorysystem.POJO.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);
}
