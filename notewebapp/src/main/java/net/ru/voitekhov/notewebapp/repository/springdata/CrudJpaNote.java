package net.ru.voitekhov.notewebapp.repository.springdata;

import net.ru.voitekhov.notewebapp.model.Category;
import net.ru.voitekhov.notewebapp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface CrudJpaNote extends JpaRepository<Note, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Note n WHERE n.id=:id AND n.category.id=:categoryId")
    int delete(@Param("id") int id, @Param("categoryId") int categoryId);

    @Query("SELECT n FROM Note n WHERE n.id=:id AND n.category.id=:categoryId")
    Note get(@Param("id") int id, @Param("categoryId") int categoryId);

    @Query("SELECT n FROM Note n WHERE n.category.id =: categoryId")
    List<Note> getAll(@Param("categoryId") int categoryId);

    @Query("SELECT c FROM Category c WHERE c.id=:id")
    Category getCategory(@Param("id") int categoryId);

}