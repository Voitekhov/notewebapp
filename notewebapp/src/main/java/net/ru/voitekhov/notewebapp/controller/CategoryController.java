package net.ru.voitekhov.notewebapp.controller;

import net.ru.voitekhov.notewebapp.model.Category;
import net.ru.voitekhov.notewebapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category/{userId}")
public class CategoryController {

    final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllCategories(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("categories", service.getAll(userId));
        model.addAttribute("userId", userId);
        return "allCategories";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Category get(@PathVariable("userId") int userId, @PathVariable("id") int id) {
        return service.get(id, userId);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("userId") int userId, @PathVariable("id") int id) {
        service.delete(id, userId);
        return "redirect:/category/" + userId;
    }

    @PostMapping("/save")
    public String save(@PathVariable("userId") int userId, Category category) {
        service.save(userId, category);
        return "redirect:/category/" + userId;
    }
}
