package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.entidades.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepo extends JpaRepository<Cuenta, Integer> {
    Optional<Cuenta> findByEmail(String email);

}