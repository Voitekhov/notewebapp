package net.ru.voitekhov.notewebapp.service.impl;

import net.ru.voitekhov.notewebapp.model.Category;
import net.ru.voitekhov.notewebapp.model.Note;
import net.ru.voitekhov.notewebapp.service.NoteService;
import net.ru.voitekhov.notewebapp.repository.springdata.CrudJpaNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class NoteServiceImpl implements NoteService {
    final CrudJpaNote repository;

    @Autowired
    public NoteServiceImpl(CrudJpaNote repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Note save(Note note, int categoryId) {
        Category category = repository.getCategory(categoryId);
        note.setCategory(category);
        if (note.isNew()) {
            return repository.save(note);
        }
        if (get(note.getId(), categoryId) != null) {
            return repository.save(note);
        }
        return null;
    }

    @Override
    public Note get(int id, int categoryId) {
        Note note = repository.get(id, categoryId);
        return note != null && note.getCategory().getId() == categoryId ? note : null;
    }

    @Transactional
    @Override
    public boolean delete(int id, int categoryId) {
        return repository.delete(id, categoryId) != 0;
    }

    @Override
    public List<Note> getAll(int categoryId) {
        return repository.getAll(categoryId);
    }

}
