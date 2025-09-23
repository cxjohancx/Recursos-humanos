package digitalFix.Backend.persistence.repository;

import digitalFix.Backend.persistence.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepositorio  extends JpaRepository<Departamento, Long> {
}
