package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepo extends JpaRepository<Paciente, Integer> {

    Paciente findByEmail(String email);

    Paciente findByCedula(String cedula);
}