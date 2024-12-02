package com.crudbook.crudbook.view;

import com.crudbook.crudbook.controller.service.interfaces.IAssuntoService;
import com.crudbook.crudbook.model.dtos.AssuntoDto;
import com.crudbook.crudbook.model.entity.Assunto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AssuntoProjectController {

    private IAssuntoService service;

    public AssuntoProjectController(IAssuntoService service) {
        this.service = service;
    }

    @GetMapping("/subjects/new")
    public String createProjectForm(Model model) {
        Assunto project = new Assunto();
        model.addAttribute("project", project);
        return "subjects-create";
    }

    @PostMapping("/subjects/new")
    public String saveProject(@ModelAttribute("project") AssuntoDto projectDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("project", projectDto);
            return "subjects-create";
        }
        service.criar(projectDto);
        return "redirect:/subjects";
    }

    @GetMapping("/subjects")
    public String listProjects(Model model) {

        List<Assunto> subjects = service.listar();

        model.addAttribute("projects", subjects);
        return "subjects-list";
    }

    @GetMapping("/subjects/{projectId}/edit")
    public String editProjectForm(@PathVariable("projectId") Long projectId, Model model) {
        var project = service.getById(projectId);
        model.addAttribute("project", project.get());
        return "subjects-edit";
    }

    @PostMapping("/subjects/{projectId}/edit")
    public String updateProject(@PathVariable("projectId") Long projectId,
                                @ModelAttribute("project") AssuntoDto project,
                                BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("project", project);
            return "subjects-edit";
        }
        service.atualizar(project, projectId);
        return "redirect:/subjects";
    }

    @GetMapping("/subjects/{projectId}/delete")
    public String deleteProject(@PathVariable("projectId")Long projectId) {
        service.deleteById(projectId);
        return "redirect:/subjects";
    }
}
