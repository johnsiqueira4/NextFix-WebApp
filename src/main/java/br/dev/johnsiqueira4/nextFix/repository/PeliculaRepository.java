package br.dev.johnsiqueira4.nextFix.repository;

import br.dev.johnsiqueira4.nextFix.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    // Optional significa que pode estar presente ou não.
    Optional<Pelicula> findByGenero(String genero);

    @Query("SELECT p FROM Pelicula p ORDER BY LOWER(p.titulo) ASC")
    List<Pelicula> findByAllByOrderByGeneroIgnoreCaseAsc();

    void deleteByDirector(Director director);

    void deleteByGenero(String genero);

}
