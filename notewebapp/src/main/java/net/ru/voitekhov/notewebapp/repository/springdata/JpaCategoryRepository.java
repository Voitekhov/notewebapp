package net.ru.voitekhov.notewebapp.repository.springdata;

import net.ru.voitekhov.notewebapp.model.Category;
import net.ru.voitekhov.notewebapp.model.User;
import net.ru.voitekhov.notewebapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaCategoryRepository implements CategoryRepository {
    @Autowired
    CrudJpaCategory repository;

    @Transactional
    @Override
    public Category save(int userId, Category category) {
        User user = repository.getUser(userId);
        category.setUser(user);
        if (category.isNew() || get(category.getId(), userId) != null) {
            return repository.save(category);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.delete(id, userId) != 0;
    }

    @Override
    public Category get(int id, int userId) {
        Category category = repository.get(id, userId);
        return category != null && category.getUser().getId() == userId ? category : null;
    }

    @Override
    public List<Category> getAll(int userId) {
        return repository.getAll(userId);
    }
}
