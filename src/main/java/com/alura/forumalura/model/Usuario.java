package com.alura.forumalura.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails { // <<-- PASSO 1: Implemente UserDetails
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    // == MÉTODOS DA INTERFACE UserDetails ==

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // PASSO 2: Defina as permissões/roles do usuário.
        // Para um sistema simples, podemos dizer que todo usuário é "ROLE_USER".
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // PASSO 3: Para este projeto, podemos deixar tudo como 'true'.
    // Em um sistema real, você poderia ter lógica para desativar ou bloquear usuários.
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