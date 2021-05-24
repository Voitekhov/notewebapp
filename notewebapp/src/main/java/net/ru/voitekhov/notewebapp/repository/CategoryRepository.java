package net.ru.voitekhov.notewebapp.repository;

import net.ru.voitekhov.notewebapp.model.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository {

    @Transactional
    public Category save(Integer userId, Category category);

    @Transactional
    public boolean delete(int id, int userId);

    public Category get(Integer id, int userId);

    public List<Category> getAll(int userId);
}
