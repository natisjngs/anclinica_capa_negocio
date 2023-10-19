package co.edu.uniquindio.modelo.entidades;

import co.edu.uniquindio.modelo.enums.Alergias;
import co.edu.uniquindio.modelo.enums.EPS;
import co.edu.uniquindio.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.modelo.enums.TipoSangre;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends Usuario implements Serializable  {
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private LocalDate fecha_nacimiento;

    @Column(nullable = false)
    private EstadoUsuario estado_usuario;

    @JoinColumn(nullable = false)
    private EPS eps;

    @JoinColumn(nullable = false)
    private TipoSangre tipo_sangre;

    @JoinColumn(nullable = false)
    private List<Alergias> alergias;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;
}