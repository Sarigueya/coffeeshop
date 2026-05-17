package coffeeshop.inventorysystem.auth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
@Data
public class AuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @Column(name = "accion")
    private String accion;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}
