package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.entidades.PQRS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PqrsRepo extends JpaRepository<PQRS, Integer> {
    List<PQRS> findByCita_PacienteCedula(String s);
}