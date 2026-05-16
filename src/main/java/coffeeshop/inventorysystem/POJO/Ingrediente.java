package coffeeshop.inventorysystem.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Entity
@Table(name = "ingrediente")
@Data
@DynamicInsert
@DynamicUpdate
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "costo_unitario")
    private Double costoUnitario;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "stock_minimo")
    private Double stockMinimo;

    @ManyToOne
    @JoinColumn(name = "unidad_medida_id")
    private UnidadMedida unidadMedida;
}
