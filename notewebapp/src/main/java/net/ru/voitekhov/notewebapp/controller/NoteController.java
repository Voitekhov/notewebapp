package net.ru.voitekhov.notewebapp.controller;


import net.ru.voitekhov.notewebapp.model.Note;
import net.ru.voitekhov.notewebapp.service.CategoryService;
import net.ru.voitekhov.notewebapp.service.NoteService;
import net.ru.voitekhov.notewebapp.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;
    private final CategoryService categoryService;

    @Autowired
    public NoteController(NoteService noteService, CategoryService categoryService) {
        this.noteService = noteService;
        this.categoryService = categoryService;
    }


    @GetMapping("/{categoryId}")
    public String getAllNotes(@PathVariable("categoryId") int categoryId, Model model) {
        int userId = SecurityUtil.getAuthUser();
        if (categoryService.get(categoryId, userId) == null) {
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
