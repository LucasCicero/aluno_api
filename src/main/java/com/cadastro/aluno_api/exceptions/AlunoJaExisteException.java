package com.cadastro.aluno_api.exceptions;

public class AlunoJaExisteException extends RuntimeException {

    public AlunoJaExisteException(String message) {
        super(message);
    }
}
