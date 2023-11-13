package com.orbitech.npvet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "perguntas",schema = "public")
@Getter @Setter
@NoArgsConstructor
public class Pergunta extends AbstractEntity{

    @Column(nullable = false,unique = true)
    private String enunciado;

    public Pergunta(String enunciado) {
        this.enunciado = enunciado;
    }
}
