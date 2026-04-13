package com.biblioteca.controller;

import com.biblioteca.model.Livro;
import com.biblioteca.service.LivroService;
import java.util.Collections;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livros")
public class LivroWebController {

    private final LivroService livroService;

    public LivroWebController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public String listar(@RequestParam(name = "q", required = false) String busca, Model model) {
        try {
            model.addAttribute("livros", livroService.buscarPorTitulo(busca));
        } catch (RuntimeException ex) {
            model.addAttribute("livros", Collections.emptyList());
            model.addAttribute("erro", "Nao foi possivel conectar ao MongoDB. Verifique se o servico esta ativo.");
        }
        model.addAttribute("busca", busca == null ? "" : busca);
        if (!model.containsAttribute("livroForm")) {
            model.addAttribute("livroForm", new Livro());
        }
        return "livros/lista";
    }

    @PostMapping
    public String cadastrar(@Valid @ModelAttribute("livroForm") Livro livro,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (bindingResult.hasErrors()) {
            try {
                model.addAttribute("livros", livroService.listarTodos());
            } catch (RuntimeException ex) {
                model.addAttribute("livros", Collections.emptyList());
                model.addAttribute("erro", "Nao foi possivel conectar ao MongoDB. Verifique se o servico esta ativo.");
            }
            model.addAttribute("busca", "");
            return "livros/lista";
        }

        try {
            livroService.salvar(livro);
            redirectAttributes.addFlashAttribute("mensagem", "Livro cadastrado com sucesso.");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("erro", "Nao foi possivel salvar. O MongoDB parece indisponivel.");
        }
        return "redirect:/livros";
    }
}
