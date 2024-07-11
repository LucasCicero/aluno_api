package com.cadastro.aluno_api.model;


//import jakarta.persistence.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

//import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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

    @NotEmpty
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "email")
    private String email;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "rua")
    private String rua;

    @NotEmpty
    @Column(name = "cep")
    private String cep;

    @Column(name="data")
    @DateTimeFormat(pattern="dd/MM/yy")
    private LocalDate dtUltimaAlteracao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aluno_curso", joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursos;

    public Aluno(Aluno alunoNew) {

        this.cpf= alunoNew.getCpf();
        this.nome = alunoNew.getNome();
        this.idade = alunoNew.getIdade();
        this.email = alunoNew.getEmail();
        this.cep = alunoNew.getCep();
    }


}

