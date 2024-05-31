package com.cadastro.aluno_api.service;

import com.cadastro.aluno_api.client.EnderecoClient;
import com.cadastro.aluno_api.model.Aluno;
import com.cadastro.aluno_api.model.dto.EnderecoDTO;
import com.cadastro.aluno_api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastro.aluno_api.exceptions.AlunoNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository ar;

    @Autowired
    private EnderecoClient enderecoClient;

    public Aluno salvarAluno(Aluno alunoNew){

        // Aluno aluno= new Aluno(alunoNew);

        EnderecoDTO enderecoResponse = enderecoClient.buscaEnderecoCep(alunoNew.getCep());
        alunoNew.setCidade(enderecoResponse.getLocalidade());
        alunoNew.setBairro(enderecoResponse.getBairro());
        alunoNew.setRua(enderecoResponse.getLogradouro());

        ar.save(alunoNew);

        return alunoNew;

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

