package net.ru.voitekhov.notewebapp.controller;

import net.ru.voitekhov.notewebapp.exception.BadRequestException;
import net.ru.voitekhov.notewebapp.model.Category;
import net.ru.voitekhov.notewebapp.service.CategoryService;
import net.ru.voitekhov.notewebapp.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//TODO transform to rest controller
@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

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
        Category category = service.get(id, userId);
        if (category == null) {
            throw new BadRequestException("Category not found");
        }
        return category;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        int userId = SecurityUtil.getAuthUser();
        if (!service.delete(id, userId)) {
            throw new BadRequestException("Category not found");
        }
        return "redirect:/category";
    }

    @PostMapping("/save")
    public String save(Category category) {
        int userId = SecurityUtil.getAuthUser();
        Category changedCategory = service.save(userId, category);
        if (changedCategory == null) {
            throw new BadRequestException(String.format("Cann`t save %s",category.getName()));
        }
        return "redirect:/category";
    }
}
