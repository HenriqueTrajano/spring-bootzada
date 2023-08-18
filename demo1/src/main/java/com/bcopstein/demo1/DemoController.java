package com.bcopstein.demo1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biblioteca")
public class DemoController{
    private List<Book> books;

    public DemoController(){
        books = new LinkedList<>();

        books.add(new Book(10,"Introdução ao Java","Huguinho Pato",2022));
        books.add(new Book(20,"Introdução ao Spring-Boot","Zezinho Pato",2020));
        books.add(new Book(15,"Principios SOLID","Luizinho Pato",2023));
        books.add(new Book(17,"Padroes de Projeto","Lala Pato",2019));
        books.add(new Book(18,"Streams and Collectors","Huguinho Pato",2023));
    }

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public String getSaudacao() {
        return "Bem vindo as biblioteca central!";
    }

    @GetMapping("/livros")
    @CrossOrigin(origins = "*")
    public List<Book> getLivros() {
        return books;
    }

    // Solucao da dinâmica
    @GetMapping("/titulos")
    @CrossOrigin(origins = "*")
    public List<String> getTitulos() {
        return books.stream()
               .map(livro->livro.titulo())
               .toList();
    }

    @GetMapping("/autores")
    @CrossOrigin(origins = "*")
    public Set<String> getAutores() {
        return Set.copyOf(books.stream()
        .map(livro->livro.autor()).toList());
    }    
}
