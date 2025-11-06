package br.dev.johnsiqueira4.nextFix.service;

import br.dev.johnsiqueira4.nextFix.models.Pelicula;
import br.dev.johnsiqueira4.nextFix.models.Plataforma;
import br.dev.johnsiqueira4.nextFix.repository.PlataformaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PlataformaService {

    private PlataformaRepository plataformaRepository;

    public Plataforma obtenerPlataformaPorId(Long id) {
        return plataformaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró la plataforma con el id: " + id)
        );
    }

    public List<Plataforma> listarPlataformas() {
        return plataformaRepository.findAll();
    }

    public Plataforma guardarPlataforma(Plataforma plataforma) {
        return plataformaRepository.save(plataforma);
    }

    @Transactional
    public void eliminarPlataforma(Long id) {
        Plataforma plataforma = plataformaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plataforma no encontrada"));

        // Desvincular la plataforma de las peliculas
        for (Pelicula pelicula : plataforma.getPeliculas()) {
            pelicula.getPlataformasDisponibles().remove(plataforma);
        }

        // Limpiar la lista de peliculas asociadas en la plataforma
        plataforma.getPeliculas().clear();

        //Finalmente, eliminamos la plataforma
        plataformaRepository.delete(plataforma);

    }



}
