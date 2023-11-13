package com.orbitech.npvet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "anamnese-pergunta",schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnamnesePergunta extends AbstractEntity {
    private String pergunta;

    @Column(nullable = false)
    private String resposta;

}
