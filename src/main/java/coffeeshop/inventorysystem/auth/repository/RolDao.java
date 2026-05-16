package coffeeshop.inventorysystem.auth.repository;

import coffeeshop.inventorysystem.auth.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);
}
