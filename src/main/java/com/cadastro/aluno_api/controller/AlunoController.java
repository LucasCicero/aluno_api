package com.cadastro.aluno_api.controller;

import com.cadastro.aluno_api.model.Aluno;
import com.cadastro.aluno_api.service.AlunoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alService;

    @PostMapping("/salvar")
    public ResponseEntity<?> insert(@RequestBody Aluno alunoNew){
        try {
            Aluno alunoNewData = alService.salvarAluno(alunoNew);
            return ResponseEntity.ok().body(alunoNewData);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getMessage(e));
        }
    }

   /* @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> buscaTodosProdutos(){

        return ResponseEntity.ok(alService.buscaTodosProdutos());
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAluno(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(alService.findById(id));

    }

    @GetMapping("/buscaNomeOuCpf")
    public Page<Aluno> search(
            @RequestParam("searchTerm") String searchTerm,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size) {
        return alService.findByNomeOrCpf(searchTerm, page, size);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> delete(@PathVariable("id") String id){
        alService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarTodos")
    public Page<Aluno> getAll() {
        log.info("Início seleciona todos os alunos");
        return alService.findAll();
    }



}

