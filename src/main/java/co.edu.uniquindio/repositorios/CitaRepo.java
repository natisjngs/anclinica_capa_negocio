package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepo extends JpaRepository<Cita, Integer> {

    List<Cita> findByMedicoCedula(String s);

    Cita findByCodigo(int codigo);

    List<Cita> findByPacienteCedula(String s);
}