package net.ru.voitekhov.notewebapp.repository.springdata;

import net.ru.voitekhov.notewebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Integer> {


    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT u FROM User u WHERE u.email=:email")
    User findByEmail(@Param("email") String email);
}
