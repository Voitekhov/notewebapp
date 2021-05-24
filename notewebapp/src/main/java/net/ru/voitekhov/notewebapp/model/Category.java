package net.ru.voitekhov.notewebapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Data
@Table(name = "categories")
public class Category extends AbstractBaseEntity {


    @Column(name = "name", nullable = false)
    @NotNull
    @NotBlank
    String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    User user;

    public Category(int id, String name) {
        super(id);
        this.name = name;
    }

    public Category() {

    }

}
