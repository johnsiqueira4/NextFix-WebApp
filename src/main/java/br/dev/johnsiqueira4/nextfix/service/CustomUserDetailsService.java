package br.dev.johnsiqueira4.nextfix.service;

import br.dev.johnsiqueira4.nextfix.models.*;
import br.dev.johnsiqueira4.nextfix.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    private final PeliculaRepository peliculaRepository;

    private final String ROL_LECTURA = "ROL_LECTURA";

    private final String ROL_DIRECTOR = "ROL_DIRECTOR";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("loadUserByUsername: Usuario no encontrado: " + username);
        }

        return User.withUsername(usuario.getUsername())
                .password(usuario.getContrasena())
                .authorities(List.of(new SimpleGrantedAuthority(usuario.getRol())))
                .build();

    }

    public Usuario guardarUsuario(Usuario usuario) {

        Usuario usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());

        if(usuarioExistente != null) {
            throw new DataIntegrityViolationException("El usuario ya se encuentra registrado!");
        }

        usuario.setRol(ROL_LECTURA);
        usuario.setContrasena(passwordEncoder().encode(usuario.getContrasena()));


        return usuarioRepository.save(usuario);
    }

    @Transactional // Só confirma/persiste as operações em banco de dados se todas as operações forem bem sucedidas
    public void eliminarUsuario(Long id) {

        Optional<Usuario> usuarioActual = usuarioRepository.findById(id);
        String usernameActual = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

        if (usuarioActual.isPresent()) {
           // Sesión activa: No permitimos eliminar el usuario que tiene la sesión actual abierta
            if (usernameActual.equals(usuarioActual.get().getUsername())) {
                throw new IllegalArgumentException("No se puede eliminar el usuario actualmente autenticado!");
            }

            //Es director: Borramos las películas asociadas
            if (usuarioActual.get().getDirector() != null) {
                peliculaRepository.deleteByDirector(usuarioActual.get().getDirector());
            }

            usuarioRepository.deleteById(id);

        }

    }

    public List<Usuario> listarUsuariosRegistrados() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> listarUsuariosRegistradosConDirectores() {
        return usuarioRepository.findByDirectorIsNotNull();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("No se encontró el usuario con id: " + id)
        );
    }

    public Usuario obtenerUsuarioPorDirector(Director director) {
        return usuarioRepository.findByDirector(director);
    }

    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public void actualizarRolUsuario(Long id, String nuevoRol) {
         Usuario usuario = obtenerUsuarioPorId(id);

         if (usuario == null) {
             throw new UsernameNotFoundException("actualizarRolUsuario: Usuario no encontrado");
         }

         usuario.setRol(nuevoRol);
         usuarioRepository.save(usuario);
    }

    public void actualizarRolUsuarioDirector(Long id, Director director) {
        Usuario usuario = obtenerUsuarioPorId(id);

        if (usuario == null) {
            throw new UsernameNotFoundException("actualizarRolUsuarioDirector: Usuario no encontrado");
        }

        usuario.setRol(ROL_DIRECTOR);
        usuario.setDirector(director);
        usuarioRepository.save(usuario);
    }

}
