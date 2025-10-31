package br.dev.johnsiqueira4.nextFix.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "La nacionalidad no puede estar en blanco")
    @Size(min = 3, max = 20)
    private String nacionalidad;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDate fechaNascimiento;

    @NotBlank(message = "El email no puede estar en blanco")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "El formato del email no es válido")
    private String email;

    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER) // nombre del atributo na clase Pelicula
    private List<Pelicula> peliculasDirigidas = new ArrayList<>();

}
