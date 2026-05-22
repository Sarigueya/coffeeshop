package coffeeshop.inventorysystem.auth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Registro de auditoría para rastrear acciones importantes del sistema.
 * <p>
 * Almacena quién realizó qué acción, cuándo y detalles adicionales.
 * Se utiliza para trazabilidad de seguridad y cumplimiento.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "audit_log")
@Data
public class AuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del registro de auditoría. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Usuario que realizó la acción. */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    /** Tipo de acción realizada (SIGNUP, LOGIN, STATUS_CHANGE, etc.). */
    @Column(name = "accion")
    private String accion;

    /** Descripción detallada de la acción realizada. */
    @Column(name = "detalle")
    private String detalle;

    /** Fecha y hora en que ocurrió la acción. */
    @Column(name = "fecha")
    private LocalDateTime fecha;
}
