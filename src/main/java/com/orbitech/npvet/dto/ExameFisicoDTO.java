package com.orbitech.npvet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExameFisicoDTO extends AbstractEntityDTO {

    private String postura;

    @NotNull(message = "O nivel de consciencia do animal é obrigatorio")
    private String nivelConsciencia;

    private Double temperaturaRetal;

    private Double frequenciaRespiratoria;

    private Double frequenciaCardiaca;

    private LocalTime tempoPreenchimentoCapilar;

    private Double pulso;

    private String hidratacao;

    private String linfSubmand;

    private String linfPreEscapulares;

    private String linfPopliteos;

    private String linfInguinais;

    private String mucosaOcular;

    private String mucosaOral;

    private String mucosaPeniana;

    private String mucosaAnal;

    @NotNull(message = "É obrigatorio informar o animal")
    private AnimalDTO animal;

    private ConsultaDTO consulta;
}
