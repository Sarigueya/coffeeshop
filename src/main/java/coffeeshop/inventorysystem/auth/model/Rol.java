package coffeeshop.inventorysystem.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Representa un rol de usuario en el sistema.
 * <p>
 * Define los permisos y el nivel de acceso: {@code admin} para
 * administradores con acceso completo, {@code user} para usuarios
 * regulares con acceso limitado.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del rol. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Nombre del rol ({@code admin} o {@code user}). Debe ser único. */
    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;
}
