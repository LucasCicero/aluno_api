package com.cadastro.aluno_api.repository;

import com.cadastro.aluno_api.model.Aluno;
import com.cadastro.aluno_api.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long>, JpaRepository<Curso, Long> {
}
