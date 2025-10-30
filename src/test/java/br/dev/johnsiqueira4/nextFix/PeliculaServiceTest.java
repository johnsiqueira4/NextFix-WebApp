package br.dev.johnsiqueira4.nextFix;

import br.dev.johnsiqueira4.nextFix.Service.*;
import br.dev.johnsiqueira4.nextFix.models.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class PeliculaServiceTest {

    private final PeliculaService peliculaService;

    private final DirectorService directorService;

    private final PlataformaService plataformaService;

    private Director directorGuardado;

    private Plataforma plataformaGuardada;

    private Pelicula peliculaTemp;

    @BeforeEach
    void setup() {
        Director director = new Director();
        director.setNacionalidad("argentina");
        director.setFechaNascimiento(LocalDate.now());
        director.setEmail("juandiaz@example.com");

        directorGuardado = directorService.guardarDirector(director);

        Plataforma plataforma = new Plataforma();
        plataforma.setNombre("Netflix");
        plataforma.setPrecio(new BigDecimal("5"));
        plataforma.setMoneda("ars");
        plataforma.setEnlace("www.netflix.com");

        plataformaGuardada = plataformaService.guardarPlataforma(plataforma);

        peliculaTemp = new Pelicula();
        peliculaTemp.setTitulo("Robocop");
        peliculaTemp.setGenero("acción");
        peliculaTemp.setFechaEstreno(LocalDate.now());
        peliculaTemp.setDirector(directorGuardado);
    }

    @Test
    void testGuardarPeliculaSinPlataforma() {
        peliculaTemp.setDirector(directorGuardado);
        Pelicula peliculaGuardada = peliculaService.guardarPelicula(peliculaTemp, directorGuardado.getId(), new ArrayList<>());

        assertNotNull(peliculaGuardada.getId());
        assertEquals("Robocop", peliculaGuardada.getTitulo());
        assertEquals("acción", peliculaGuardada.getGenero());
        assertEquals(directorGuardado.getId(), peliculaGuardada.getDirector().getId());
    }

    @Test
    void testGuardarPeliculaConPlataformas() {
        List<Long> idPlataformasDisponibles = new ArrayList<>();
        idPlataformasDisponibles.add(plataformaGuardada.getId());

        Pelicula peliculaGuardada = peliculaService.guardarPelicula(peliculaTemp, directorGuardado.getId(), idPlataformasDisponibles);

        assertNotNull(peliculaGuardada.getId());
        assertEquals("Robocop", peliculaGuardada.getTitulo());
        assertEquals("acción", peliculaGuardada.getGenero());
        assertEquals(directorGuardado.getId(), peliculaGuardada.getDirector().getId());
        assertFalse(peliculaGuardada.getPlataformasDisponibles().isEmpty());
    }

    @Test
    void testListarPeliculas() {
        assertFalse(peliculaService.listarPeliculas().isEmpty());
    }

}
