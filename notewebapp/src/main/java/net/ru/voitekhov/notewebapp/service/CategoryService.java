package net.ru.voitekhov.notewebapp.service;

import net.ru.voitekhov.notewebapp.model.Category;
import net.ru.voitekhov.notewebapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category save(int userId, Category category) {
        return repository.save(userId, category);
    }


    public boolean delete(int id, int userId) {
        return repository.delete(id, userId);
    }


    public Category get(int id, int userId) {
        return repository.get(id, userId);
    }


    public List<Category> getAll(int userId) {
        return repository.getAll(userId);
    }
}
