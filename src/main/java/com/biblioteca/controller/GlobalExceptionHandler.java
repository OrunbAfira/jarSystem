package com.biblioteca.controller;

import com.biblioteca.model.Livro;
import java.util.Collections;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DataAccessException.class, IllegalStateException.class})
    public String handleDatabaseExceptions(Exception ex, Model model) {
        model.addAttribute("erro", "Nao foi possivel conectar ao MongoDB. Verifique se o servico esta ativo.");
        model.addAttribute("livros", Collections.emptyList());
        model.addAttribute("livroForm", new Livro());
        model.addAttribute("busca", "");
        return "livros/lista";
    }
}
