package coffeeshop.inventorysystem.auth.repository;

import coffeeshop.inventorysystem.auth.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad {@link Rol}.
 *
 * @since 1.0
 */
public interface RolDao extends JpaRepository<Rol, Integer> {

    /**
     * Busca un rol por su nombre.
     *
     * @param nombre nombre del rol ({@code "admin"} o {@code "user"})
     * @return el rol si existe, {@code null} en caso contrario
     */
    Rol findByNombre(String nombre);
}
