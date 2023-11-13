package com.orbitech.npvet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AnamneseDTO extends AbstractEntityDTO {

    @NotNull(message = "O animal deve ser informado!")
    private AnimalDTO animalDTO;

    @NotNull(message = "O tutor deve ser informado!")
    private TutorDTO tutorDTO;

    @NotNull(message = "O médico deve ser informado!")
    private UsuarioDTO veterinarioDTO;

    private String queixaPrincipal;

    private List<AnamneseHistoricoDTO> historicoProgressoMedico;

    private String alimentacao;

    private String contactantes;

    private String ambiente;

    private String vacinacao;

    private String vermifugacao;

    private String sistemaRespiratorio;

    private String sistemaCardiovascular;

    private String sistemaUrinario;

    private String sistemaReprodutor;

    private String sistemaLocomotor;

    private String sistemaNeurologico;

    private String pele;

    private String olhos;

    private String ouvidos;

    private List<AnamnesePerguntaDTO> anamnesePerguntas = new ArrayList<>();


}
