package br.dev.johnsiqueira4.nextFix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Genera: toString, equals, hashcode, getters y los setters  -- //@ToString.Exclude
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)
    private Long id;

    private String username;

    private String contrasena;

    private String rol;

    private String nombre;

    private String apellido;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Director director;

}
