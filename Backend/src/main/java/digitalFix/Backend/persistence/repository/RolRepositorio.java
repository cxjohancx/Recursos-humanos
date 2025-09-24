package digitalFix.Backend.persistence.repository;

import digitalFix.Backend.persistence.entity.Rol;
import digitalFix.Backend.persistence.entity.RolEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RolRepositorio extends JpaRepository<Rol, Long> {

    List<Rol> findRolByRolEnumIn(List<String> roles);
}
