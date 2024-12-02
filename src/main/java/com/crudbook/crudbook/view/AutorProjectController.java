package com.crudbook.crudbook.view;

import com.crudbook.crudbook.controller.service.interfaces.IAutorService;
import com.crudbook.crudbook.model.dtos.AutorDto;
import com.crudbook.crudbook.model.entity.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AutorProjectController {

    private IAutorService service;

    public AutorProjectController(IAutorService service) {
        this.service = service;
    }

    @GetMapping("/autors/new")
    public String createProjectForm(Model model) {
        Autor project = new Autor();
        model.addAttribute("project", project);
        return "autors-create";
    }

    @PostMapping("/autors/new")
    public String saveProject(@ModelAttribute("project") AutorDto projectDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("project", projectDto);
            return "autors-create";
        }
        service.criar(projectDto);
        return "redirect:/autors";
    }

    @GetMapping("/autors")
    public String listProjects(Model model) {

        List<Autor> autors = service.listar();

        model.addAttribute("projects", autors);
        return "autors-list";
    }

    @GetMapping("/autors/{projectId}/edit")
    public String editProjectForm(@PathVariable("projectId") Long projectId, Model model) {
        var project = service.getById(projectId);
        model.addAttribute("project", project.get());
        return "autors-edit";
    }

    @PostMapping("/autors/{projectId}/edit")
    public String updateProject(@PathVariable("projectId") Long projectId,
                                @ModelAttribute("project") AutorDto project,
                                BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("project", project);
            return "autors-edit";
        }
        service.atualizar(project, projectId);
        return "redirect:/autors";
    }

    @GetMapping("/autors/{projectId}/delete")
    public String deleteProject(@PathVariable("projectId")Long projectId) {
        service.deleteById(projectId);
        return "redirect:/autors";
    }
}
