package com.cadastro.aluno_api.service;

import com.cadastro.aluno_api.client.EnderecoClient;
import com.cadastro.aluno_api.exceptions.AlunoJaExisteException;
import com.cadastro.aluno_api.model.Aluno;
import com.cadastro.aluno_api.model.dto.EnderecoDTO;
import com.cadastro.aluno_api.repository.AlunoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cadastro.aluno_api.exceptions.AlunoNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AlunoService {

    @Autowired
    private AlunoRepository ar;

    @Autowired
    private EnderecoClient enderecoClient;

    public Aluno salvarAluno(Aluno alunoNew){

        if (ar.findAlunoByCpf(alunoNew.getCpf()) != null) {
            log.error("Aluno já existe na base de dados");
            throw new AlunoJaExisteException("Já existe um aluno cadastrado com o CPF: " + alunoNew.getCpf());

        }


        EnderecoDTO enderecoResponse = enderecoClient.buscaEnderecoCep(alunoNew.getCep());
        alunoNew.setCidade(enderecoResponse.getLocalidade());
        alunoNew.setBairro(enderecoResponse.getBairro());
        alunoNew.setRua(enderecoResponse.getLogradouro());

        alunoNew.setDtUltimaAlteracao(LocalDate.now());

        ar.save(alunoNew);

        return alunoNew;

    }

    public Aluno findById(String id){
        return ar.findById(id)
                .orElseThrow(AlunoNotFoundException::new);

    }

    public void delete(String id){
        Aluno aluno = ar.findById(id)
                .orElseThrow(AlunoNotFoundException::new);

        ar.delete(aluno);
    }


    public Page<Aluno> findAll(){
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");
        return new PageImpl<>(
                ar.findAll(),
                pageRequest, size);
    }



    public Page<Aluno> findByNomeOrCpf(String searchTerm, int page, int size){
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");
        return ar.findByNomeOrCidade(searchTerm.toLowerCase(),
                pageRequest);

    }

    //pesquisa por data de ultima alteração
}

