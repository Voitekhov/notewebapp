package net.ru.voitekhov.notewebapp.service;

import net.ru.voitekhov.notewebapp.model.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {

    public Category save(int userId, Category category);

    public boolean delete(int id, int userId);

    public Category get(int id, int userId);

    public List<Category> getAll(int userId);
}
