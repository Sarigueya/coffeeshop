package coffeeshop.inventorysystem.auth.repository;

import coffeeshop.inventorysystem.auth.dto.UserWrapper;
import coffeeshop.inventorysystem.auth.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio JPA para la entidad {@link User}.
 * <p>
 * Incluye consultas personalizadas para autenticación y
 * administración de usuarios, incluyendo las definidas
 * como {@code @NamedQuery} en la entidad {@link User}.
 * </p>
 *
 * @since 1.0
 */
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email correo del usuario
     * @return el usuario si existe, {@code null} en caso contrario
     */
    User findByEmailId(@Param("email") String email);

    /**
     * Obtiene todos los usuarios con rol {@code user}.
     *
     * @return lista de usuarios (sin datos sensibles)
     */
    List<UserWrapper> getALLUser();

    /**
     * Obtiene los correos de todos los administradores.
     *
     * @return lista de correos de administradores
     */
    List<String> getAllAdmin();

    /**
     * Actualiza el estado de un usuario.
     *
     * @param status nuevo estado ({@code "true"} activo, {@code "false"} inactivo)
     * @param id     ID del usuario a actualizar
     * @return número de registros actualizados
     */
    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

    /**
     * Busca un usuario por su correo electrónico (para recuperación de contraseña).
     *
     * @param email correo del usuario
     * @return el usuario si existe, {@code null} en caso contrario
     */
    User findByEmail(String email);
}
