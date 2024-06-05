package com.cadastro.aluno_api.service;

import com.cadastro.aluno_api.client.EnderecoClient;
import com.cadastro.aluno_api.exceptions.AlunoJaExisteException;
import com.cadastro.aluno_api.model.Aluno;
import com.cadastro.aluno_api.model.dto.EnderecoDTO;
import com.cadastro.aluno_api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastro.aluno_api.exceptions.AlunoNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository ar;

    @Autowired
    private EnderecoClient enderecoClient;

    public Aluno salvarAluno(Aluno alunoNew){

        if (ar.findAlunoByCpf(alunoNew.getCpf()) != null) {
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


    public List<Aluno> findAll(){
        // return (List<Aluno>) ar.findAll();
        return ar.findAll();
    }
}

