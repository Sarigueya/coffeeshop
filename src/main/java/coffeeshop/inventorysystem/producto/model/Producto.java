package coffeeshop.inventorysystem.producto.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Entity
@Table(name = "producto")
@Data
@DynamicInsert
@DynamicUpdate
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", unique = true)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;
}
