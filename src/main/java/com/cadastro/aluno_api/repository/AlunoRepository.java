package com.cadastro.aluno_api.repository;

import com.cadastro.aluno_api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, String>, JpaRepository<Aluno, String> {

    Aluno findAlunoByCpf(String cpf);
}
