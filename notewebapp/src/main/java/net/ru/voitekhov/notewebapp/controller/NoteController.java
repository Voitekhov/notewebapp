package net.ru.voitekhov.notewebapp.controller;


import net.ru.voitekhov.notewebapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NoteController {

    final NoteService service;

    @Autowired
    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String getAllNotes(@PathVariable("id") int categoryId, Model model) {
        // handle empty category
        model.addAttribute("notes", service.getAll(categoryId));
        return "allNotes";

    }
}
