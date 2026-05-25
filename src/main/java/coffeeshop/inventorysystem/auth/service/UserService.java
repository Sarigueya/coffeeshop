package coffeeshop.inventorysystem.auth.service;

import coffeeshop.inventorysystem.auth.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Servicio para la gestión de usuarios y autenticación.
 * <p>
 * Maneja registro, inicio de sesión, actualización de estados,
 * cambio y recuperación de contraseñas, y promoción a administrador.
 * Integra JWT para autenticación y auditoría de acciones.
 * </p>
 *
 * @since 1.0
 */
public interface UserService {

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request datos del usuario a registrar
     * @return {@code 200 OK} si se registró exitosamente,
     *         {@code 400 BAD_REQUEST} si el correo ya existe o datos inválidos
     */
    ResponseEntity<String> signUp(SignupRequest request);

    /**
     * Inicia sesión con credenciales de usuario.
     *
     * @param request credenciales: email y contraseña
     * @return token JWT con {@code 200 OK} si es exitoso,
     *         {@code 400 BAD_REQUEST} si las credenciales son incorrectas o cuenta inactiva
     */
    ResponseEntity<String> login(LoginRequest request);

    /**
     * Obtiene la lista de usuarios con rol {@code user}.
     *
     * @return lista de usuarios (sin datos sensibles)
     */
    ResponseEntity<List<UserWrapper>> getALLUser();

    /**
     * Actualiza el estado de un usuario (activar/desactivar).
     *
     * @param request ID del usuario y nuevo estado
     * @return {@code 200 OK} si se actualizó,
     *         {@code 400 BAD_REQUEST} si no existe o es admin
     */
    ResponseEntity<String> update(UpdateUserRequest request);

    /**
     * Verifica si el token JWT actual es válido.
     *
     * @return {@code "true"} con {@code 200 OK} si es válido
     */
    ResponseEntity<String> checkToken();

    /**
     * Cambia la contraseña del usuario autenticado.
     *
     * @param request contraseña actual y nueva contraseña
     * @return {@code 200 OK} si se actualizó,
     *         {@code 400 BAD_REQUEST} si la contraseña actual es incorrecta
     */
    ResponseEntity<String> changePassword(ChangePasswordRequest request);

    /**
     * Envía una contraseña temporal al correo del usuario.
     *
     * @param request correo electrónico del usuario
     * @return {@code 200 OK} siempre (por seguridad, no revela si el correo existe)
     */
    ResponseEntity<String> forgotPassword(ForgotPasswordRequest request);

    /**
     * Promueve un usuario a rol {@code admin}.
     *
     * @param id identificador del usuario a promover
     * @return {@code 200 OK} si se promovió,
     *         {@code 400 BAD_REQUEST} si no existe o ya es admin
     */
    ResponseEntity<String> assignAdmin(Integer id);
}
