package net.ru.voitekhov.notewebapp.controller;

import net.ru.voitekhov.notewebapp.model.Category;
import net.ru.voitekhov.notewebapp.service.CategoryService;
import net.ru.voitekhov.notewebapp.util.SecurityUtil;
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
        int userId = SecurityUtil.getAuthUser();
        model.addAttribute("categories", service.getAll(userId));
        model.addAttribute("userId", userId);
        return "allCategories";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Category get(@PathVariable("id") int id) {
        int userId = SecurityUtil.getAuthUser();
        return service.get(id, userId);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        int userId = SecurityUtil.getAuthUser();
        service.delete(id, userId);
        return "redirect:/category";
    }

    @PostMapping("/save")
    public String save(Category category) {
        int userId = SecurityUtil.getAuthUser();
        service.save(userId, category);
        return "redirect:/category";
    }
}
