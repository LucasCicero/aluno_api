package com.cadastro.aluno_api.model;

import com.cadastro.aluno_api.util.CursosEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "nome_curso")
    private CursosEnum nomeCurso;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aluno_curso", joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunos;
}
