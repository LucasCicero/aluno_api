package com.cadastro.aluno_api.controller;

import com.cadastro.aluno_api.model.Aluno;
import com.cadastro.aluno_api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alService;

    @PostMapping("/save")
    public ResponseEntity<Aluno> insert(@RequestBody Aluno alunoNew){
        Aluno alunoNewData = alService.salvarAluno(alunoNew);
        return ResponseEntity.ok().body(alunoNewData);
    }

   /* @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> buscaTodosProdutos(){

        return ResponseEntity.ok(alService.buscaTodosProdutos());
    }*/


    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> delete(@PathVariable("id") String id){
        alService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<Aluno> getAll() {
        return alService.findAll();
    }
}

