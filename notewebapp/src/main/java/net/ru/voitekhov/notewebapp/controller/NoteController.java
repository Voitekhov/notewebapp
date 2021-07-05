package net.ru.voitekhov.notewebapp.controller;


import net.ru.voitekhov.notewebapp.model.Note;
import net.ru.voitekhov.notewebapp.service.impl.CategoryServiceImpl;
import net.ru.voitekhov.notewebapp.service.impl.NoteServiceImpl;
import net.ru.voitekhov.notewebapp.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//TODO transform to rest controller
@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteServiceImpl noteService;
    private final CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public NoteController(NoteServiceImpl noteService, CategoryServiceImpl categoryServiceImpl) {
        this.noteService = noteService;
        this.categoryServiceImpl = categoryServiceImpl;
    }


    @GetMapping("/{categoryId}")
    public String getAllNotes(@PathVariable("categoryId") int categoryId, Model model) {
        int userId = SecurityUtil.getAuthUser();
        if (categoryServiceImpl.get(categoryId, userId) == null) {
            return "NotAccess";
        }
        model.addAttribute("notes", noteService.getAll(categoryId));
        model.addAttribute("categoryId", categoryId);
        return "allNotes";
    }

    @PostMapping("/save/{categoryId}")
    public String save(Note note, @PathVariable("categoryId") int categoryId) {
        noteService.save(note, categoryId);
        return "redirect:/note/" + categoryId;
    }

    @GetMapping("/get/{categoryId}/{id}")
    @ResponseBody
    public Note get(@PathVariable("categoryId") int categoryId, @PathVariable("id") int id) {
        return noteService.get(id, categoryId);
    }

    @GetMapping("delete/{categoryId}/{id}")
    public String delete(@PathVariable("categoryId") int categoryId, @PathVariable("id") int id) {
        noteService.delete(id, categoryId);
        return "redirect:/note/" + categoryId;
    }

    @GetMapping("back")
    public String back() {
        return "redirect:/category";
    }


}
