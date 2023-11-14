package co.edu.uniquindio.modelo.entidades;

import co.edu.uniquindio.modelo.enums.Ciudad;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario extends Cuenta implements Serializable {
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, length = 10)
    private String cedula;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(length = 15)
    private String telefono;

    @Lob
    @Column(nullable = false)
    private String url_foto;

    @Column(nullable = false)
    private Ciudad ciudad;
}