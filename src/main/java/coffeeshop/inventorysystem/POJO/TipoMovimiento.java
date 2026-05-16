package coffeeshop.inventorysystem.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Entity
@Table(name = "tipo_movimiento")
@Data
@DynamicInsert
@DynamicUpdate
public class TipoMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre_tipo")
    private String nombreTipo;

    @Column(name = "operacion")
    private String operacion;
}
