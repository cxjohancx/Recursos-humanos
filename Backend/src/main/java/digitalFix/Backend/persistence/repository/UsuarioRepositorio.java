package digitalFix.Backend.persistence.repository;

import digitalFix.Backend.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByNombreUsuario(String nombreUsuario);
}
