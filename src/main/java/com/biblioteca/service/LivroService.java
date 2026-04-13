package com.biblioteca.service;

import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos() {
        try {
            return livroRepository.findAll();
        } catch (DataAccessException ex) {
            return Collections.emptyList();
        }
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return listarTodos();
        }
        try {
            return livroRepository.findByTituloContainingIgnoreCase(titulo.trim());
        } catch (DataAccessException ex) {
            return Collections.emptyList();
        }
    }

    public Livro salvar(Livro livro) {
        try {
            return livroRepository.save(livro);
        } catch (DataAccessException ex) {
            throw new IllegalStateException("Nao foi possivel salvar. O MongoDB parece indisponivel.", ex);
        }
    }
}
