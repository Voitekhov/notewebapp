package net.ru.voitekhov.notewebapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractBaseEntity {

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @NotNull
    private String first_name;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank
    @NotNull
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @NotNull
    private String password;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime registrate = LocalDateTime.now();

    @Column(name = "enabled", nullable = false, columnDefinition = "true")
    @NotNull
    private boolean enabled;

    @Column(name = "role", columnDefinition = "varchar(5) default 'USER'", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public User(Integer id, String first_name, String email, String password) {
        super(id);
        this.first_name = first_name;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

}