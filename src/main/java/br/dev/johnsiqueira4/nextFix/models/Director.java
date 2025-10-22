package br.dev.johnsiqueira4.nextFix.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data // Genera: toString, equals, hashcode, getters y los setters  -- //@ToString.Exclude
@NoArgsConstructor
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nacionalidad;

    private LocalDate fechaNascimiento;

    private String email;

    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER) // nombre del atributo na clase Pelicula
    private List<Pelicula> peliculasDirigidas = new ArrayList<>();

}
