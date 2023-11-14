package co.edu.uniquindio.modelo.entidades;

import co.edu.uniquindio.modelo.enums.Especialidad;
import co.edu.uniquindio.modelo.enums.EstadoUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Entity
//@DiscriminatorValue("medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Usuario implements Serializable {
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private Especialidad especialidad;

    @Column(nullable = false)
    private EstadoUsuario estado_usuario;

    @Column(nullable = false)
    private LocalTime hora_inicio;

    @Column(nullable = false)
    private LocalTime hora_fin;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

    @OneToMany(mappedBy = "medico")
    private List<DiaLibre> dias_libres;

    @OneToMany(mappedBy = "medico")
    private List<Horario> horarios;
}