package net.ru.voitekhov.notewebapp.service;

import net.ru.voitekhov.notewebapp.model.User;
import net.ru.voitekhov.notewebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user) {
        return repository.save(user);
    }


    public boolean delete(int userId) {
        return repository.delete(userId);
    }


    public User get(int userId) {
        return repository.get(userId);
    }


    public List<User> getAll() {
        return repository.getAll();
    }


    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean confirmPassword(String password1, String password2) {
        return password1.equals(password2);
    }

    public boolean isFreeEmail(String email) {
        return repository.findByEmail(email) == null;


    }
}
