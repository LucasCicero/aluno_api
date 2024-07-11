package com.cadastro.aluno_api.controller;

import com.cadastro.aluno_api.model.Aluno;
import com.cadastro.aluno_api.model.Curso;
import com.cadastro.aluno_api.service.AlunoService;
import com.cadastro.aluno_api.service.CursoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/alunos")
public class CursoController {


    @Autowired
    private CursoService csService;

    @GetMapping("/buscarTodosCursos")
    public Page<Curso> getAll() {
        log.info("Início seleciona todos os cursos");
        return csService.findAll();
    }

    @PostMapping("/salvarCurso")
    public ResponseEntity<?> insert(@RequestBody Curso cursoNew){
        try {
            Curso cursoNewData = csService.salvarCurso(cursoNew);
            return ResponseEntity.ok().body(cursoNewData);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getMessage(e));
        }
    }

}
