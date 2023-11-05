package com.orbitech.npvet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO extends AbstractEntityDTO{
    @NotNull(message = "O logradouro deve ser informado!")
    private String logradouro;
    @NotNull(message = "O bairro deve ser informado!")
    private String bairro;
    @NotNull(message = "A cidade deve ser informado!")
    private String cidade;
    @NotNull(message = "O estado deve ser informado!")
    private String estado;
    @NotNull(message = "O país deve ser informado!")
    private String pais;
    private String numero;
    @NotNull(message = "O CEP deve ser informado!")
    private String cep;
    private String complemento;
    @JsonIgnore // Para evitar a auto-referencia e um JSON infinito
    private List<TutorDTO> residentes;
}
