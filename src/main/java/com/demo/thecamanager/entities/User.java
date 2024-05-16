package com.demo.thecamanager.entities;

import com.demo.thecamanager.enums.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
@NoArgsConstructor()
@AllArgsConstructor()
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Email
    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Size(max = 11)
    @CPF
    @NotNull
    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @NotNull
    @Column(name = "pwd", nullable = false, length = Integer.MAX_VALUE)
    private String pwd;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "punishment_id")
    private Punishment punishment;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Book> tbBooks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Loan> tbLoans = new LinkedHashSet<>();

    @Column(name = "profile", nullable = false)
    private Profile profile = Profile.CLIENT;

    public User(String cpf, String pwd) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.equals(this.profile, Profile.ADMIN.getDescription())) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_LIBRARIAM"),
                    new SimpleGrantedAuthority("ROLE_CLIENT")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
        }
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return cpf;
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