package com.cadastro.aluno_api.model;


//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

//import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

    @Column(name = "cpf")
    private String cpf;

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

    @Column(name="data")
    @DateTimeFormat(pattern="dd/MM/yy")
    private LocalDate dtUltimaAlteracao;

    public Aluno(Aluno alunoNew) {

        this.cpf= alunoNew.getCpf();
        this.nome = alunoNew.getNome();
        this.idade = alunoNew.getIdade();
        this.cep = alunoNew.getCep();
    }


}

