package net.ru.voitekhov.notewebapp.repository.springdata;

import net.ru.voitekhov.notewebapp.model.Category;
import net.ru.voitekhov.notewebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CrudJpaCategory extends JpaRepository<Category, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Category c WHERE c.id=:id AND c.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT c FROM Category c WHERE c.id=:id AND c.user.id=:userId")
    Category get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT c FROM Category c WHERE c.user.id =:userId order by c.name")
    List<Category> getAll(@Param("userId") int userId);

    @Query("SELECT u FROM User u WHERE u.id=:id")
    User getUser(@Param("id") int userId);


}
