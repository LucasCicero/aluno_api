package com.cadastro.aluno_api.model;


//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aluno implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "idade")
    private Integer idade;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "rua")
    private String rua;
    @Column(name = "cep")
    private String cep;

    public Aluno(Aluno alunoNew) {

        this.nome = alunoNew.getNome();
        this.idade = alunoNew.getIdade();
        this.cep = alunoNew.getCep();
    }


}

