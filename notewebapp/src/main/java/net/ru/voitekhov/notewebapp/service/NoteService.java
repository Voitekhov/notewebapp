package net.ru.voitekhov.notewebapp.service;

import net.ru.voitekhov.notewebapp.model.Note;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteService {

    public Note save(Note note, int categoryId);

    public Note get(int id, int categoryId);

    public boolean delete(int id, int categoryId);

    public List<Note> getAll(int categoryId);

}
