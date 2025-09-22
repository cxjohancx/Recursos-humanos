package digitalFix.Backend.persistence.repository;

import digitalFix.Backend.persistence.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajadorRepositorio extends JpaRepository<Trabajador, Long> {
}
