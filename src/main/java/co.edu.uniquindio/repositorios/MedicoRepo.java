package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepo extends JpaRepository<Medico, Integer> {

    Medico findByCorreo(String email);

    Medico findByCedula(String cedula);
}