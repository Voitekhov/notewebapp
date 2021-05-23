package net.ru.voitekhov.notewebapp.repository;

import net.ru.voitekhov.notewebapp.model.Category;

import java.util.List;

public interface CategoryRepository {

    public Category save(int userId, Category category);


    public boolean delete(int id, int userId);

    public Category get(int id, int userId);

    public List<Category> getAll(int userId);
}
