package co.edu.uniquindio.modelo.entidades;

import co.edu.uniquindio.modelo.enums.EstadoPQRS;
import co.edu.uniquindio.modelo.enums.TipoPQRS;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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

public class PQRS implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, length = 30)
    private LocalDateTime fecha_creacion;

    @Column(nullable = false)
    private TipoPQRS tipo_pqrs;

    @Lob
    @Column(nullable = false)
    private String motivo;

    @Column(nullable = false, length = 30)
    private EstadoPQRS estado_pqrs;

    @ManyToOne
    private Cita cita;

    @OneToMany(mappedBy = "pqrs")
    private List<Mensaje> mensajes;
}