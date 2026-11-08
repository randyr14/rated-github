package com.gcu.rated.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.rated.model.MediaEntry;
import com.gcu.rated.service.MediaEntryService;

import jakarta.validation.Valid;

// Handles the pages and the create, read, update, and delete actions.
@Controller
public class MediaEntryController {
    private final MediaEntryService service;

    public MediaEntryController(MediaEntryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String library(@RequestParam(required = false) String type, Model model) {
        model.addAttribute("entries", service.findEntries(type));
        model.addAttribute("selectedType", type == null || type.isBlank() ? "All" : type);
        return "index";
    }

    @GetMapping("/entries/add")
    public String addForm(Model model) {
        model.addAttribute("entry", new MediaEntry());
        model.addAttribute("pageTitle", "Add to your library");
        return "entry-form";
    }

    @PostMapping("/entries/save")
    public String save(@Valid @ModelAttribute("entry") MediaEntry entry,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", entry.getMediaId() == null
                    ? "Add to your library" : "Edit your entry");
            return "entry-form";
        }
        service.save(entry);
        return "redirect:/";
    }

    @GetMapping("/entries/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("entry", service.findById(id));
        return "entry-details";
    }

    @GetMapping("/entries/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("entry", service.findById(id));
        model.addAttribute("pageTitle", "Edit your entry");
        return "entry-form";
    }

    @PostMapping("/entries/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
