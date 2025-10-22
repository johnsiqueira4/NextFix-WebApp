package br.dev.johnsiqueira4.nextFix.repository;

import br.dev.johnsiqueira4.nextFix.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
