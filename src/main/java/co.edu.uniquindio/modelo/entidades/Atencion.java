package co.edu.uniquindio.modelo.entidades;

import co.edu.uniquindio.modelo.enums.Especialidad;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atencion implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @JoinColumn(nullable = false)
    @Lob
    private String diagnostico;

    @JoinColumn(nullable = false)
    @Lob()
    private String tratamiento;

    @JoinColumn(nullable = false)
    @Lob()
    private String notasMedicas;

    @Column(nullable = false, length = 30)
    private LocalDateTime fecha_atencion;

    @Column(nullable = false, length = 50)
    private Especialidad especializacion;

    @OneToOne
    private Cita cita;
}