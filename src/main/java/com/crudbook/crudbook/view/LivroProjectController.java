package com.crudbook.crudbook.view;

import com.crudbook.crudbook.controller.document.DataMapper;
import com.crudbook.crudbook.controller.document.DocumentGenerate;
import com.crudbook.crudbook.controller.service.interfaces.IAssuntoService;
import com.crudbook.crudbook.controller.service.interfaces.IAutorService;
import com.crudbook.crudbook.model.dtos.CreatedCompletedLivroDto;
import com.crudbook.crudbook.model.dtos.CreatedLivroDto;
import com.crudbook.crudbook.model.entity.Livro;
import com.crudbook.crudbook.controller.service.interfaces.ILivroService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class LivroProjectController {

    private ILivroService service;
    private IAutorService autorService;
    private IAssuntoService assuntoService;

    @Autowired
    private DocumentGenerate documentGenerator;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private DataMapper dataMapper;

    public LivroProjectController(ILivroService service, IAutorService autorService, IAssuntoService assuntoService) {
        this.service = service;
        this.autorService = autorService;
        this.assuntoService = assuntoService;
    }

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("layout");
        return mv;
    }

    @GetMapping("/projects/new")
    public String createProjectForm(Model model) {
        Livro project = new Livro();
        model.addAttribute("project", project);
        return "projects-create";
    }

    @PostMapping("/projects/new")
    public String saveProject(@ModelAttribute("project") CreatedCompletedLivroDto projectDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("project", projectDto);
            return "projects-create";
        }

        service.criar(projectDto);
        return "redirect:/projects";
    }

    @GetMapping("/projects/book/new")
    public String createProjectCompleteForm(Model model) {

        Livro project = new Livro();
        model.addAttribute("project", project);

        var optionsAutors = autorService.listar();
        model.addAttribute("optionsAutors", optionsAutors);

        var optionsSubjects = assuntoService.listar();
        model.addAttribute("optionsSubjects", optionsSubjects);

        return "projects-book-create";
    }

    @PostMapping("/projects/book/new")
    public String saveCompleteProject(@ModelAttribute("project") CreatedCompletedLivroDto projectDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("project", projectDto);
            return "projects-book-create";
        }

        service.criar(projectDto);
        return "redirect:/projects";
    }

    @GetMapping("/projects")
    public String listProjects(Model model) {

        List<Livro> projects = service.listar();

        model.addAttribute("projects", projects);
        return "projects-list";
    }

    @GetMapping("/projects/report")
    public String report(Model model) {

        var projects = service.reportList();

        model.addAttribute("projects", projects);
        return "report-list";
    }

    @GetMapping("/projects/{projectId}/edit")
    public String editProjectForm(@PathVariable("projectId") Long projectId, Model model) {
        var project = service.getById(projectId);
        model.addAttribute("project", project.get());

        var optionsAutors = autorService.listar();
        model.addAttribute("optionsAutors", optionsAutors);

        var optionsSubjects = assuntoService.listar();
        model.addAttribute("optionsSubjects", optionsSubjects);

        return "projects-edit";
    }

    @PostMapping("/projects/{projectId}/edit")
    public String updateProject(@PathVariable("projectId") Long projectId,
                                @ModelAttribute("project") CreatedCompletedLivroDto project,
                                BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("project", project);
            return "projects-edit";
        }

        service.atualizar(project, projectId);
        return "redirect:/projects";
    }

    @GetMapping("/projects/{projectId}/delete")
    public String deleteProject(@PathVariable("projectId")Long projectId) {
        service.deleteById(projectId);

        return "redirect:/projects";
    }

    @GetMapping("/generate/document")
    public String generateDocument(Model model) {

        var projects = service.reportList();

        String finalHtml = null;

        Context dataContext = dataMapper.setData(projects);

        model.addAttribute("projects", projects);
        finalHtml = springTemplateEngine.process("report", dataContext);

        documentGenerator.htmlToPdf(finalHtml);

        return "redirect:/projects/report";
    }

}
