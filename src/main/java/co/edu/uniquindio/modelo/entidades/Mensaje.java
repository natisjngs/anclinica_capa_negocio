package co.edu.uniquindio.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private LocalDateTime fecha_creacion;

    @Lob
    @Column(nullable = false)
    private String texto;

    @OneToOne
    private Mensaje mensaje;

    @JoinColumn(nullable = false)
    @ManyToOne
    private PQRS pqrs;

    @ManyToOne
    private Cuenta cuenta;
}