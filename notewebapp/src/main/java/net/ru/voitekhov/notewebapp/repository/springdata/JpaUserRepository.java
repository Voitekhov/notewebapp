package net.ru.voitekhov.notewebapp.repository.springdata;

import net.ru.voitekhov.notewebapp.model.User;
import net.ru.voitekhov.notewebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

    final CrudJpaUser repository;

    @Autowired
    public JpaUserRepository(CrudJpaUser repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(int userId) {
        return repository.delete(userId) != 0;
    }

    @Override
    public User get(int userId) {
        return repository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
