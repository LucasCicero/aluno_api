package com.cadastro.aluno_api.service;

import com.cadastro.aluno_api.exceptions.AlunoJaExisteException;
import com.cadastro.aluno_api.model.Aluno;
import com.cadastro.aluno_api.model.Curso;
import com.cadastro.aluno_api.model.dto.EnderecoDTO;
import com.cadastro.aluno_api.repository.AlunoRepository;
import com.cadastro.aluno_api.repository.CursoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class CursoService {

    @Autowired
    private CursoRepository cr;

    public Page<Curso> findAll(){
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nomeCurso");
        return new PageImpl<>(
                cr.findAll(),
                pageRequest, size);
    }

    public Curso salvarCurso(Curso cursoNew){

     //   if (ar.findAlunoByCpf(alunoNew.getCpf()) != null) {
       //     log.error("Aluno já existe na base de dados");
        //    throw new AlunoJaExisteException("Já existe um aluno cadastrado com o CPF: " + alunoNew.getCpf());

       // }


     /*   EnderecoDTO enderecoResponse = enderecoClient.buscaEnderecoCep(alunoNew.getCep());
        alunoNew.setCidade(enderecoResponse.getLocalidade());
        alunoNew.setBairro(enderecoResponse.getBairro());
        alunoNew.setRua(enderecoResponse.getLogradouro());

        alunoNew.setDtUltimaAlteracao(LocalDate.now());*/

        cr.save(cursoNew);

        return cursoNew;

    }
}
