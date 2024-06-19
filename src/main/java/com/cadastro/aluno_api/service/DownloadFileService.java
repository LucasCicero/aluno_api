package com.cadastro.aluno_api.service;

import com.cadastro.aluno_api.exceptions.AlunoJaExisteException;
import com.cadastro.aluno_api.exceptions.AlunoNotFoundException;
import com.cadastro.aluno_api.model.Aluno;
import com.cadastro.aluno_api.repository.AlunoRepository;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import jakarta.servlet.http.Cookie;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class DownloadFileService {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AlunoRepository ar;

    public ResponseEntity<?> gerarArquivo(HttpServletRequest request,HttpServletResponse response) throws IOException{
        try {
            Page<Aluno> listaAlunos= alunoService.findAll();

            if (listaAlunos==null || listaAlunos.isEmpty()){
                throw new AlunoNotFoundException();
            }

            FileWriter outputfile = new FileWriter("LISTA_ALUNOS");
            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = montarCabecalhoListaAluno();
            writer.writeNext(header);

            for(Aluno aluno: listaAlunos){
                writer.writeNext(montarLinhaListaAluno(aluno));
            }

            writer.close();

            Cookie cookie = new Cookie("downloadDocumento", "finalizado");
            cookie.setPath("/");
           // response.addCookie(cookie);

          //  response.setContentType("text/csv");
         //   response.addHeader("Content-disposition", "attachment; filename=\""+ outputfile +".csv\"");
         //   response.flushBuffer();

            return ResponseEntity.status(HttpStatus.OK).body("ok");
        } catch (IOException e){

        return null;

        }


    }

    public String[] montarCabecalhoListaAluno(){
        String[] cabecalho= {"CPF","NOME","IDADE","CIDADE", "BAIRRO","RUA","CEP","DATA"};
        return cabecalho;
    }

    public String[] montarLinhaListaAluno(Aluno aluno){

        String[] linha= {aluno.getCpf(),
                aluno.getNome(),
              //  aluno.getIdade().toString(),
                aluno.getCidade(),
                aluno.getBairro(),
                aluno.getRua(),
                aluno.getCep(),
              //  aluno.getDtUltimaAlteracao().toString()


        };

        return linha;
    }


}
