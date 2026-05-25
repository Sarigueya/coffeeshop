package coffeeshop.inventorysystem.kardex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

/**
 * Representa un tipo de movimiento de inventario.
 * <p>
 * Define los diferentes tipos de operaciones que se pueden realizar
 * sobre el inventario, como entrada de stock, salida por producción,
 * venta, ajuste, etc. Cada movimiento tiene un {@link TipoMovimiento}
 * que determina si es una operación de entrada o salida.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "movimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del tipo de movimiento. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Nombre descriptivo del movimiento (ej: "Entrada", "Salida", "Venta"). */
    @Column(name = "nombre_movimiento")
    private String nombreMovimiento;

    /** Descripción detallada del tipo de movimiento. */
    @Column(name = "descripcion")
    private String descripcion;

    /** Clasificación del movimiento: entrada (+) o salida (-). */
    @ManyToOne
    @JoinColumn(name = "tipo_movimiento_id")
    private TipoMovimiento tipoMovimiento;
}
