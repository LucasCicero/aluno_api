package com.cadastro.aluno_api.model.user;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String login;
    private String password;
    private UserRole role;
}
