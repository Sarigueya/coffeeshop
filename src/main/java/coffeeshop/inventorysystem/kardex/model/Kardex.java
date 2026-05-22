package coffeeshop.inventorysystem.kardex.model;

import coffeeshop.inventorysystem.auth.model.User;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Registro de movimiento de inventario para un ingrediente (Kardex).
 * <p>
 * Cada entrada en el kardex representa un cambio en el stock de un
 * ingrediente, registrando la cantidad movida, los saldos anterior
 * y posterior, el tipo de movimiento y el usuario responsable.
 * </p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "kardex")
@Data
@DynamicInsert
@DynamicUpdate
public class Kardex implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del registro de kardex. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Ingrediente al que corresponde este movimiento de inventario. */
    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    /** Tipo de movimiento asociado (entrada, salida, venta, etc.). */
    @ManyToOne
    @JoinColumn(name = "movimiento_id")
    private Movimiento movimiento;

    /** Usuario que realizó el movimiento. */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    /** Cantidad movida (positiva para entrada, negativa para salida). */
    @Column(name = "cantidad")
    private Double cantidad;

    /** Saldo del ingrediente antes del movimiento. */
    @Column(name = "saldo_anterior")
    private Double saldoAnterior;

    /** Saldo del ingrediente después del movimiento. */
    @Column(name = "saldo_nuevo")
    private Double saldoNuevo;

    /** Fecha y hora en que se registró el movimiento. */
    @Column(name = "fecha")
    private LocalDateTime fecha;
}
