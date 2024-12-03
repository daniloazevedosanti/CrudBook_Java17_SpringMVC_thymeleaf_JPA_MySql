package com.crudbook.crudbook.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<String> crudEventNotFound(ApiRequestException crudException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error!");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> crudBookExceptio(ApiRequestException crudException){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error!");
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView projetException(Model model, ApiRequestException crudException){
        model.addAttribute("project", crudException.getMessage());
        return new ModelAndView("error");
    }

}
