package com.orbitech.npvet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "anamnese_historico",schema = "public")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnamneseHistorico extends AbstractEntity{
    @Column(name = "progresso_medico")
    private String progressoMedico;

    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;

    @PrePersist
    public void prePersistDataAtualizacao() {
        this.dataAtualizacao = LocalDate.now();
    }

}
