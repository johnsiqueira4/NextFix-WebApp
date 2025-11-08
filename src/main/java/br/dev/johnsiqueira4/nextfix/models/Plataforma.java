package br.dev.johnsiqueira4.nextfix.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(min = 3, max = 20)
    private String nombre;

    @NotNull(message = "El precio no puede ser nulo")
    private BigDecimal precio;

    @NotBlank(message = "La moneda no puede estar en blanco")
    private String moneda; // ars, usd, etc

    @NotBlank(message = "El enlace no puede estar en blanco")
    @Pattern(
            regexp = "^(https?://)?([\\w\\-]+\\.)+[a-zA-Z]{2,}(/[\\w\\-._~:?#@!$&'()*+,;=]*)?$",
            message = "El enlace debe ser una  URL válida"
    )
    private String enlace;

    // fetch é como se carregará os objetos em memória EARGER ou LAZY
    @ManyToMany(mappedBy = "plataformasDisponibles", fetch = FetchType.EAGER)
    private List<Pelicula> peliculas = new ArrayList<>();

}
