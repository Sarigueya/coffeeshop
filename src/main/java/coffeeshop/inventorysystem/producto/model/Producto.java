package coffeeshop.inventorysystem.producto.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

/**
 * Representa un producto del menú que se puede vender.
 * <p>
 * Cada producto puede tener una {@link Receta} opcional que define
 * los ingredientes y cantidades necesarias para su preparación.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "producto")
@Data
@DynamicInsert
@DynamicUpdate
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del producto, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Nombre del producto. Debe ser único en el sistema. */
    @Column(name = "nombre", unique = true)
    private String nombre;

    /** Descripción detallada del producto. */
    @Column(name = "descripcion")
    private String descripcion;

    /** Precio de venta al público. */
    @Column(name = "precio_venta")
    private Double precioVenta;

    /** Indica si el producto está activo ({@code true}) o desactivado ({@code false}). */
    @Column(name = "activo")
    private Boolean activo;

    /** Receta asociada al producto, define los ingredientes necesarios para su elaboración. */
    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;
}
