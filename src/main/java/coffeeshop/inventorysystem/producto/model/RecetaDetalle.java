package coffeeshop.inventorysystem.producto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

/**
 * Representa la relación entre una receta y uno de sus ingredientes,
 * especificando la cantidad requerida.
 * <p>
 * Actúa como tabla intermedia entre {@link Receta} e {@link Ingrediente},
 * formando parte de la composición de un producto.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "receta_detalle")
@Data
@DynamicInsert
@DynamicUpdate
public class RecetaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del detalle, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Receta a la que pertenece este detalle. */
    @ManyToOne
    @JoinColumn(name = "receta_id")
    @JsonBackReference
    private Receta receta;

    /** Ingrediente utilizado en la receta. */
    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    /** Cantidad requerida del ingrediente para esta receta (en la unidad de medida del ingrediente). */
    @Column(name = "cantidad_requerida")
    private Double cantidadRequerida;
}
