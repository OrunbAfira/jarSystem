package com.biblioteca.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "livros")
public class Livro {

    @Id
    private String id;

    @NotBlank(message = "Titulo e obrigatorio")
    private String titulo;

    @NotBlank(message = "Autor e obrigatorio")
    private String autor;

    @Min(value = 0, message = "Quantidade deve ser maior ou igual a zero")
    private int quantidade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
