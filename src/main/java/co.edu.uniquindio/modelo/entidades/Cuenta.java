package co.edu.uniquindio.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance (strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Email
    @Column(nullable = false, unique = true, length = 80)
    private String email;

    @Column(nullable = false, length = 20)
    private String password;

    @OneToMany(mappedBy = "cuenta")
    private List<Mensaje> mensajes;
}