package com.cadastro.aluno_api.controller;

import com.cadastro.aluno_api.service.DownloadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/alunos")
public class DownloadFileController {

    @Autowired
    private DownloadFileService downloadCsvService;

    @GetMapping("/downloadCsvAlunos")
    public ResponseEntity<?> downloadFileAllAlunos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(downloadCsvService.gerarArquivo(request,response));
    }
}
