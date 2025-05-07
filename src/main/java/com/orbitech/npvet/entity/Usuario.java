package com.orbitech.npvet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orbitech.npvet.oauth.model.entity.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", schema = "public")
public class Usuario extends AbstractEntity implements UserDetails {

    @Column(length = 100, name = "nome")
    private String nome;

    @Column(unique = true, length = 14, name = "cpf")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(unique = true, nullable = false, length = 30, name = "username")
    private String username;

    @Column( nullable = false, name = "password")
    private String password;

    @OneToMany(mappedBy = "veterinario")
    @JsonIgnoreProperties("veterinario")
    private List<Anamnese> anamneses = new ArrayList<>();

    @OneToMany
    private  List<Consulta> consultas = new ArrayList<>();

    @Getter @Setter
    private LocalDateTime createdAt;

    @Getter @Setter
    private LocalDateTime updatedAt;

    @Getter @Setter
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "usuario")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
