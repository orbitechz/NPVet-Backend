package com.orbitech.npvet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PerguntaDTO extends AbstractEntityDTO{

    @NotNull(message = "O enunciado deve ser informado!")
    private String enunciado;
}
