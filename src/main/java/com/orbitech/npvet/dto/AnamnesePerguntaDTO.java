package com.orbitech.npvet.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AnamnesePerguntaDTO extends AbstractEntityDTO{

    @NotNull(message = "A pergunta deve ser informada!")
    private String pergunta;

    @NotNull(message = "A resposta deve ser informada!")
    private String resposta;

}
