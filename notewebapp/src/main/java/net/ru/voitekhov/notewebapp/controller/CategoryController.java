package net.ru.voitekhov.notewebapp.controller;

import net.ru.voitekhov.notewebapp.model.Category;
import net.ru.voitekhov.notewebapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories", service.getAll(100001));
        return "allCategories";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Category get(@PathVariable("id") int id) {
        return service.get(id, 100001);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        service.delete(id, 100001);
        return "redirect:/category";
    }

    @PostMapping("/save")
    public String save(Category category) {
        service.save(100001, category);
        return "redirect:/category";
    }
}
