package net.ru.voitekhov.notewebapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notes")
public class Note extends AbstractBaseEntity {

    @Column(name = "title", nullable = false)
    @NotNull
    @NotBlank
    String title;

    @Column(name = "link")
    String link;

    @Column(name = "text", nullable = false)
    String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    Category category;

    @Column(name = "created", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime created = LocalDateTime.now();

    public Note(int id, String title, String link, String text) {
        super(id);
        this.title = title;
        this.link = link;
        this.text = text;
    }

    public Note() {
    }

}
