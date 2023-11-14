package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.entidades.Atencion;
import co.edu.uniquindio.modelo.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtencionRepo extends JpaRepository<Atencion, Integer> {

    Cita findByCodigo(int codigo);
}