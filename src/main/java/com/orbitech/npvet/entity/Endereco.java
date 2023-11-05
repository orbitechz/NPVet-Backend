package com.orbitech.npvet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco", schema = "public")
public class Endereco extends AbstractEntity{

    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String pais;
    private String numero;
    @Column(nullable = false)
    private String cep;
    private String complemento;
    @ManyToMany(mappedBy = "enderecos")
    @JsonIgnore // Para evitar a auto-referencia e um JSON infinito
    private List<Tutor> residentes;
}
