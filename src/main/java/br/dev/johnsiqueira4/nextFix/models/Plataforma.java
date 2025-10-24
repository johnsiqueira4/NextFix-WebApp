package br.dev.johnsiqueira4.nextFix.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Data // Genera: toString, equals, hashcode, getters y los setters  -- //@ToString.Exclude
@NoArgsConstructor
@AllArgsConstructor
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    private BigDecimal precio;

    private String moneda; // ars, usd, etc

    private String enlace;

    // fetch é como se carregará os objetos em memória EARGER ou LAZY
    @ManyToMany(mappedBy = "plataformasDisponibles", fetch = FetchType.EAGER)
    private List<Pelicula> peliculas = new ArrayList<>();

}
