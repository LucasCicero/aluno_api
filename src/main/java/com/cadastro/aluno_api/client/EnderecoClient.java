package com.cadastro.aluno_api.client;

import com.cadastro.aluno_api.model.dto.EnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface EnderecoClient {

    @GetMapping("{cep}/json")
    EnderecoDTO buscaEnderecoCep(@PathVariable("cep") String cep);
}
