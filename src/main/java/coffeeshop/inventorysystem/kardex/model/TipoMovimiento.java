package coffeeshop.inventorysystem.kardex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

/**
 * Clasifica los movimientos de inventario en entrada o salida.
 * <p>
 * Determina la dirección del movimiento en el kardex:
 * {@code "entrada"} incrementa el stock y {@code "salida"} lo decrementa.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "tipo_movimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class TipoMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del tipo de movimiento. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Nombre del tipo (ej: "Entrada", "Salida"). */
    @Column(name = "nombre_tipo")
    private String nombreTipo;

    /** Indica la operación: {@code "entrada"} para incrementar stock, {@code "salida"} para decrementar. */
    @Column(name = "operacion")
    private String operacion;
}
