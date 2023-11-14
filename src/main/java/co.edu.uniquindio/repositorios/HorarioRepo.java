package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepo extends JpaRepository<Horario, Long> {
    Horario findByCodigo(int codigo);
}