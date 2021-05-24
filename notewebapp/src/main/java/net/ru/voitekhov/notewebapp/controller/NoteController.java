package net.ru.voitekhov.notewebapp.controller;


import net.ru.voitekhov.notewebapp.model.Note;
import net.ru.voitekhov.notewebapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    final NoteService service;

    @Autowired
    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/{categoryId}")
    public String getAllNotes(@PathVariable("categoryId") int categoryId, Model model) {
        // handle empty category
        model.addAttribute("notes", service.getAll(categoryId));
        model.addAttribute("categoryId", categoryId);
        return "allNotes";
    }

    @PostMapping("/save/{categoryId}")
    public String save(Note note, @PathVariable("categoryId") int categoryId) {
        service.save(note, categoryId);
        return "redirect:/note/" + categoryId;
    }

    @GetMapping("/get/{categoryId}/{id}")
    @ResponseBody
    public Note get(@PathVariable("categoryId") int categoryId, @PathVariable("id") int id) {
        return service.get(id, categoryId);
    }

    @GetMapping("delete/{categoryId}/{id}")
    public String delete(@PathVariable("categoryId") int categoryId, @PathVariable("id") int id) {
        service.delete(id, categoryId);
        return "redirect:/note/" + categoryId;
    }


}
