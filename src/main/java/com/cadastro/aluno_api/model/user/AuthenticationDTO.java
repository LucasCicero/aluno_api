package com.cadastro.aluno_api.model.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {
    private String login;
    private String password;
}
