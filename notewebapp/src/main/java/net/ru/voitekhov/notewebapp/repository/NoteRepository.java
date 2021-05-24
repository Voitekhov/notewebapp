package net.ru.voitekhov.notewebapp.repository;

import net.ru.voitekhov.notewebapp.model.Note;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteRepository {

    @Transactional
    public Note save(Note note, int categoryId);


    public Note get(int id, int categoryId);

    @Transactional
    public boolean delete(int id, int categoryId);

    public List<Note> getAll(int categoryId);

}
