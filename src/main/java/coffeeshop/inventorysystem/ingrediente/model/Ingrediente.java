package coffeeshop.inventorysystem.ingrediente.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

/**
 * Representa un ingrediente utilizado en la preparación de productos.
 * <p>
 * Los ingredientes tienen un costo unitario, un stock mínimo de alerta
 * y se miden en una {@link UnidadMedida} específica. Su nombre debe
 * ser único en el sistema.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "ingrediente")
@Data
@DynamicInsert
@DynamicUpdate
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del ingrediente, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Nombre del ingrediente. Debe ser único en el sistema. */
    @Column(name = "nombre", unique = true)
    private String nombre;

    /** Costo unitario del ingrediente. */
    @Column(name = "costo_unitario")
    private Double costoUnitario;

    /** Indica si el ingrediente está activo ({@code true}) o desactivado ({@code false}). */
    @Column(name = "activo")
    private Boolean activo;

    /** Stock mínimo para alertas de inventario. */
    @Column(name = "stock_minimo")
    private Double stockMinimo;

    /** Unidad de medida en la que se expresa este ingrediente (ej: g, ml, kg, unidades). */
    @ManyToOne
    @JoinColumn(name = "unidad_medida_id")
    private UnidadMedida unidadMedida;
}
