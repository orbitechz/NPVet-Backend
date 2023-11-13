package com.orbitech.npvet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbitech.npvet.entity.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO extends AbstractEntityDTO {
    @NotNull(message = "Você precisa selecionar um paciente.")
    private AnimalDTO animal;
    @NotNull(message = "Você precisa selecionar um tutor.") 
    private TutorDTO tutor;
    private AnamneseDTO anamnese;
    @JsonIgnore
    private List<ExameFisicoDTO>examesFisicos = new ArrayList<>();
    @NotNull(message = "Informe a data.")
    private LocalDateTime data;
    @NotNull (message = "Você precisar informar o status.")
    private Status status;
    @NotNull(message = "Você precisa informar o Médico Veterinário!")
    private UsuarioDTO veterinario;
}
