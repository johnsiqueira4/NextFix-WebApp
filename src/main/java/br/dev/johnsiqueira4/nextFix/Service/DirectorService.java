package br.dev.johnsiqueira4.nextFix.Service;

import br.dev.johnsiqueira4.nextFix.models.Director;
import br.dev.johnsiqueira4.nextFix.repository.DirectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectorService {

    private DirectorRepository directorRepository;

    public Director obtenerDirectorPorId(Long id){
        return directorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró el director: " + id)
        );
    }

    public List<Director> listarDirectores() {
        return directorRepository.findAll();
    }

    public Director guardarDirector(Director director){
        return directorRepository.save(director);
    }

}
