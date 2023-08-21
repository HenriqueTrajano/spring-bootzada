package com.bcopstein.demo1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        books.add(new Book(18,"Aluga Carros","Vraulio Silva",2022));
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
               .map(livro->livro.title())
               .toList();
    }

    @GetMapping("/autores")
    @CrossOrigin(origins = "*")
    public Set<String> getAutores() {
        return Set.copyOf(books.stream()
        .map(livro->livro.author()).toList());
    }

    @GetMapping("/livros-autor")
    @CrossOrigin(origins = "*")
    public List<Book> getBookByAuthor(@RequestParam(value = "autor") String author) {
        String auxAuthor = author.toLowerCase();
        return books.stream().filter(book -> book.author().toLowerCase().equals(auxAuthor)).toList();
    } 

    @GetMapping("/livros-autor/{author}/ano/{year}")
    @CrossOrigin(origins = "*")
    public List<Book> getBookByAuthorAndYear(@PathVariable(value = "author") String author, @PathVariable(value = "year") int year) {
        String auxAuthor = author.toLowerCase();
        return books.stream().filter(book -> book.author().toLowerCase().equals(auxAuthor) && book.year() == year).toList();
    } 

    @PostMapping("/novo-livro")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Boolean> addNewBook(@RequestBody final Book book){
        books.add(book);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
