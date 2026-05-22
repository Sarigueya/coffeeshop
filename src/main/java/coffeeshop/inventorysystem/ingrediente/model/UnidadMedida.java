package coffeeshop.inventorysystem.ingrediente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

/**
 * Representa una unidad de medida para ingredientes.
 * <p>
 * Define cómo se miden los ingredientes en el sistema,
 * por ejemplo: gramos (g), mililitros (ml), kilogramos (kg),
 * litros (L), unidades, etc.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "unidad_medida")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class UnidadMedida implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único de la unidad de medida. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Nombre descriptivo de la unidad de medida (ej: "gramos", "mililitros", "unidades"). */
    @Column(name = "nombre_unidad")
    private String nombreUnidad;
}
