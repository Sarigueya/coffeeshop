package coffeeshop.inventorysystem.auth.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email=:email")

@NamedQuery(
        name = "User.getALLUser",
        query = "select new coffeeshop.inventorysystem.auth.dto.UserWrapper(u.id, u.name, u.email, u.contactNumber, u.status) from User u where u.rol.nombre='user'"
)

@NamedQuery(
        name = "User.getAllAdmin",
        query = "select u.email from User u where u.rol.nombre='admin'"
)

@NamedQuery(
        name = "User.updateStatus",
        query = "update User u set u.status=:status where u.id=:id"
)

/**
 * Representa un usuario del sistema.
 * <p>
 * Los usuarios pueden tener rol {@code admin} o {@code user} y su estado
 * puede ser {@code "true"} (activo) o {@code "false"} (pendiente/desactivado).
 * El primer usuario registrado obtiene rol ADMIN automáticamente.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "user")
@Data
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del usuario. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Nombre completo del usuario. */
    @Column(name = "name")
    private String name;

    /** Número de contacto telefónico. */
    @Column(name = "contactNumber")
    private String contactNumber;

    /** Correo electrónico del usuario, usado como identificador para el inicio de sesión. */
    @Column(name = "email")
    private String email;

    /** Contraseña cifrada del usuario. */
    @Column(name = "password")
    private String password;

    /** Estado del usuario: {@code "true"} activo, {@code "false"} inactivo/pendiente. */
    @Column(name = "status")
    private String status;

    /** Rol asignado al usuario ({@code admin} o {@code user}). */
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
