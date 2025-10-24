package br.dev.johnsiqueira4.nextFix.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;


@Entity
@Data // Genera: toString, equals, hashcode, getters y los setters  -- //@ToString.Exclude
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String titulo;

    private String genero;

    //@EqualsAndHashCode.Exclude
    private LocalDate fechaEstreno;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @ToString.Exclude // Para resolver o erro de recursividade Lombok, de Memory Leak em relações @ManyToOne
    private Director director;

    @ManyToMany
    @JoinTable(
            name = "Pelicula_Plataforma",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "plataforma_id")
    )
    @ToString.Exclude // Para resolver o erro de recursividade Lombok, de Memory Leak em relações @ManyToMany
    @Builder.Default // "No permite que genere una lista vacia"
    private List<Plataforma> plataformasDisponibles = new ArrayList<>(); // netflix, hbo, cine, tv, etc

}
