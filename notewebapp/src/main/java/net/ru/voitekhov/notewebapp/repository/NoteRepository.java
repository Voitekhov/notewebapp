package net.ru.voitekhov.notewebapp.repository;

import net.ru.voitekhov.notewebapp.model.Note;

import java.util.List;

public interface NoteRepository {

    public Note save(Note note, int categoryId);


    public Note get(int id, int categoryId);

    public boolean delete(int id, int categoryId);

    public List<Note> getAll(int categoryId);

}
