package br.dev.johnsiqueira4.nextFix.repository;

import br.dev.johnsiqueira4.nextFix.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    Usuario findByDirector(Director director);

    List<Usuario> findByDirectorIsNotNull();

}
