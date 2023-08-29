package com.bcopstein.demo1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private IBookCollection books;

    @Autowired
    public DemoController(IBookCollection books){
        this.books = books;
    }

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public String getSaudacao() {
        return "Bem vindo as biblioteca central!";
    }

    @GetMapping("/livros")
    @CrossOrigin(origins = "*")
    public List<Book> getLivros() {
        return books.getAllBooks();
    }

    // Solucao da dinâmica
    @GetMapping("/titulos")
    @CrossOrigin(origins = "*")
    public List<String> getTitulos() {
        return books.getAllBookTitles();
    }

    @GetMapping("/autores")
    @CrossOrigin(origins = "*")
    public Set<String> getAutores() {
        return books.getAllBookAuthors();
    }

    @GetMapping("/livros-autor")
    @CrossOrigin(origins = "*")
    public List<Book> getBookByAuthor(@RequestParam(value = "autor") String author) {
        return books.getBooksByAuthor(author);
    } 

    @GetMapping("/livros-autor/{author}/ano/{year}")
    @CrossOrigin(origins = "*")
    public List<Book> getBookByAuthorAndYear(@PathVariable(value = "author") String author, @PathVariable(value = "year") int year) {
        return books.getBooksByAuthorAndYear(author, year);
    } 

    @PostMapping("/novo-livro")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Boolean> addNewBook(@RequestBody final Book book){
        if (books.addBook(book)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(true);
        }
    }
}
