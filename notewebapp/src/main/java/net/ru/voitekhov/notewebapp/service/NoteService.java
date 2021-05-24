package net.ru.voitekhov.notewebapp.service;

import net.ru.voitekhov.notewebapp.model.Note;
import net.ru.voitekhov.notewebapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    NoteRepository repository;

    public Note save(Note note, int categoryId) {
        return repository.save(note, categoryId);
    }


    public Note get(int id, int categoryId) {
        return repository.get(id, categoryId);
    }


    public boolean delete(int id, int categoryId) {
        return repository.delete(id, categoryId);
    }


    public List<Note> getAll(int categoryId) {
        return repository.getAll(categoryId);
    }
}
