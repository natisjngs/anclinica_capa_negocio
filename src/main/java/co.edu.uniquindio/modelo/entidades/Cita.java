package co.edu.uniquindio.modelo.entidades;

import co.edu.uniquindio.modelo.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, length = 30)
    private LocalDateTime fecha_creacion;

    @Column(nullable = false, length = 30)
    private LocalDateTime fecha_cita;

    @Column(nullable = false, length = 30)
    private String motivo;

    @JoinColumn(nullable = false)
    private EstadoCita estado_cita;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Paciente paciente;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Medico medico;

    @OneToMany(mappedBy = "cita")
    private List<PQRS> pqrs;

    @OneToOne(mappedBy = "cita")
    private Atencion atencion;
}