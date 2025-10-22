package br.dev.johnsiqueira4.nextFix.repository;

import br.dev.johnsiqueira4.nextFix.models.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
    // CrudRepository (apenas operacoes CRUD)
    // PagingAndSortingRepository (CRUD + Paginacao e Ordenaçao)
    // JpaRepository (CRUD + Paginacao e Ordenaçao + Métodos adicionais)

}
