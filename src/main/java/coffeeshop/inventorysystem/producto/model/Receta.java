package coffeeshop.inventorysystem.producto.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

/**
 * Representa una receta que define la composición de un producto.
 * <p>
 * Una receta contiene una lista de {@link RecetaDetalle} que especifican
 * los ingredientes y las cantidades requeridas. Cada producto puede
 * tener asociada una única receta.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "receta")
@Data
@DynamicInsert
@DynamicUpdate
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único de la receta, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Nombre de la receta, normalmente coincide con el nombre del producto. */
    @Column(name = "nombre")
    private String nombre;

    /** Descripción opcional de la receta. */
    @Column(name = "descripcion")
    private String descripcion;

    /** Indica si la receta está activa ({@code true}) o desactivada ({@code false}). */
    @Column(name = "activo")
    private Boolean activo;

    /** Lista de ingredientes con sus cantidades requeridas para esta receta. */
    @OneToMany(mappedBy = "receta", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RecetaDetalle> detalles;
}
