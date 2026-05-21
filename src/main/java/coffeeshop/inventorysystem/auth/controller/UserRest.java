package coffeeshop.inventorysystem.auth.controller;

import coffeeshop.inventorysystem.auth.dto.*;
import coffeeshop.inventorysystem.auth.dto.UserWrapper;
import coffeeshop.inventorysystem.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/user")
@Tag(name = "Usuarios", description = "Gestión de usuarios, autenticación y autorización")
public interface UserRest {

    @PostMapping(path = "/signup")
    @Operation(summary = "Registrar usuario", description = "Crea una nueva cuenta de usuario. El primer usuario registrado obtiene rol ADMIN automáticamente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "El correo ya existe o datos inválidos")
    })
    @SecurityRequirement(name = "")
    ResponseEntity<String> signUp(@Valid @RequestBody SignupRequest request);

    @PostMapping(path = "/login")
    @Operation(summary = "Iniciar sesión", description = "Autentica al usuario con email y contraseña. Devuelve un token JWT si las credenciales son válidas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login exitoso, token JWT generado"),
            @ApiResponse(responseCode = "400", description = "Credenciales incorrectas o cuenta pendiente de aprobación")
    })
    @SecurityRequirement(name = "")
    ResponseEntity<String> login(@Valid @RequestBody LoginRequest request);

    @GetMapping(path = "/get")
    @Operation(summary = "Obtener usuarios", description = "Retorna la lista de todos los usuarios con rol 'user'. Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado — se requiere rol ADMIN")
    })
    ResponseEntity<List<UserWrapper>> getAllUser();

    @PostMapping(path = "/update")
    @Operation(summary = "Actualizar estado de usuario", description = "Activa o desactiva un usuario. Notifica por correo a los administradores. Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado de usuario actualizado"),
            @ApiResponse(responseCode = "400", description = "El ID de usuario no existe"),
            @ApiResponse(responseCode = "403", description = "No se puede modificar a otro administrador")
    })
    ResponseEntity<String> update(@Valid @RequestBody UpdateUserRequest request);

    @GetMapping(path = "/checkToken")
    @Operation(summary = "Validar token JWT", description = "Verifica si el token JWT actual es válido. Retorna 'true' si es válido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Token válido"),
            @ApiResponse(responseCode = "401", description = "Token inválido o expirado")
    })
    ResponseEntity<String> checkToken();

    @PostMapping(path = "/changePassword")
    @Operation(summary = "Cambiar contraseña", description = "Cambia la contraseña del usuario autenticado actualmente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contraseña actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Contraseña anterior incorrecta")
    })
    ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequest request);

    @PostMapping(path = "/forgotPassword")
    @Operation(summary = "Recuperar contraseña", description = "Envía una contraseña temporal al correo del usuario.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Correo enviado si el usuario existe"),
            @ApiResponse(responseCode = "400", description = "Correo inválido")
    })
    @SecurityRequirement(name = "")
    ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request);

    @PostMapping(path = "/assign-admin/{id}")
    @Operation(summary = "Asignar rol ADMIN", description = "Promueve un usuario existente a administrador. Requiere rol ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario promovido a administrador"),
            @ApiResponse(responseCode = "400", description = "Usuario no encontrado o ya es administrador"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado — se requiere rol ADMIN")
    })
    ResponseEntity<String> assignAdmin(@PathVariable Integer id);
}
