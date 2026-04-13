package com.biblioteca.repository;

import com.biblioteca.model.Livro;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LivroRepository extends MongoRepository<Livro, String> {

    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
