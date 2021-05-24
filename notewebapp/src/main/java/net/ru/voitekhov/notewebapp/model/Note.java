package net.ru.voitekhov.notewebapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note extends AbstractBaseEntity {

    @Column(name = "title", nullable = false)
    @NotNull
    @NotBlank
    String title;

    @Column(name = "link")
    String link;

    @Column(name = "text", nullable = false)
    @NotNull
    @NotBlank
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

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getText() {
        return text;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
