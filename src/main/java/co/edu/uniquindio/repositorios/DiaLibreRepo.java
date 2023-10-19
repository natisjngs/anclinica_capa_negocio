package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.entidades.DiaLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaLibreRepo extends JpaRepository<DiaLibre, Integer> {
}