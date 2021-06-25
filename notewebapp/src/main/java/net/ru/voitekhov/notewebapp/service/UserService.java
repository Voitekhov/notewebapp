package net.ru.voitekhov.notewebapp.service;

import net.ru.voitekhov.notewebapp.model.User;

import java.util.List;

public interface UserService {

    public User save(User user);

    public boolean delete(int userId);

    public User get(int userId);

    public List<User> getAll();

    public User findByEmail(String email);
}
