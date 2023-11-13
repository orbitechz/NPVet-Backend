package com.orbitech.npvet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbitech.npvet.entity.Genero;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO extends AbstractEntityDTO {
    @NotNull(message = "O nome do tutor deve ser informado!")
    @Length(min = 3, max = 50, message = "O nome do tutor deve ter entre 3 e 50 caracteres")
    private String nome;
    @NotNull(message = "O gênero deve ser informado!")
    private Genero genero;
    @NotNull(message = "O CPF do tutor deve ser informado!")
    @CPF(message = "O CPF informado é inválido!")
    private String cpf;
    @Length(max = 20, message = "O RG deve ter no máximo 20 caracteres")
    private String rg;
    @Email(message = "O e-mail informado é inválido!")
    @Length(max = 100, message = "O e-mail deve ter apenas até 100 caracteres")
    private String email;
    @JsonIgnore
    private List<AnamneseDTO> anamneses = new ArrayList<>();
    @NotNull(message = "Informe no mínimo 1 telefone de contato!")
    @NotEmpty(message = "Informe no mínimo 1 telefone de contato!")
    private List<ContatoDTO> telefones;
    @NotNull(message = "Informe no mínimo 1 endereço!")
    @NotEmpty(message = "Informe no mínimo 1 endereço!")
    private List<EnderecoDTO> enderecos;
}
