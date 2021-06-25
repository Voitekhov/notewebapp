package net.ru.voitekhov.notewebapp.service.impl;

import net.ru.voitekhov.notewebapp.model.User;
import net.ru.voitekhov.notewebapp.service.UserService;
import net.ru.voitekhov.notewebapp.repository.springdata.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    final JpaUserRepository repository;

    @Autowired
    public UserServiceImpl(JpaUserRepository repository) {
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
