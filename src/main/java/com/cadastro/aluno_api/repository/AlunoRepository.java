package com.cadastro.aluno_api.repository;

import com.cadastro.aluno_api.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, String>, JpaRepository<Aluno, String> {

    Aluno findAlunoByCpf(String cpf);

    @Query("FROM Aluno c " + "WHERE LOWER(c.nome) like %:searchTerm% " + " OR LOWER(c.cidade) like %:searchTerm%")
    Page<Aluno> findByNomeOrCidade(@Param("searchTerm") String searchTerm,
                                    Pageable pageable);

}
