package coffeeshop.inventorysystem.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "kardex")
@Data
@DynamicInsert
@DynamicUpdate
public class Kardex implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "movimiento_id")
    private Movimiento movimiento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "saldo_anterior")
    private Double saldoAnterior;

    @Column(name = "saldo_nuevo")
    private Double saldoNuevo;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}
