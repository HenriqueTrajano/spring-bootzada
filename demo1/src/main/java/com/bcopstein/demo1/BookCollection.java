package com.bcopstein.demo1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class BookCollection implements IBookCollection {
    private LinkedList<Book> books;
    
    public BookCollection() {
        this.books = new LinkedList<>();

        books.add(new Book(10,"Introdução ao Java","Huguinho Pato",2022));
        books.add(new Book(20,"Introdução ao Spring-Boot","Zezinho Pato",2020));
        books.add(new Book(15,"Principios SOLID","Luizinho Pato",2023));
        books.add(new Book(17,"Padroes de Projeto","Lala Pato",2019));
        books.add(new Book(18,"Streams and Collectors","Huguinho Pato",2023));
        books.add(new Book(18,"Aluga Carros","Vraulio Silva",2022));
    }

    @Override
    public LinkedList<Book> getAllBooks() {
        return this.books;
    }

    @Override
    public List<String> getAllBookTitles() {
        return books.stream()
        .map(livro->livro.title())
        .toList();
    }


    @Override
    public Set<String> getAllBookAuthors() {
        return Set.copyOf(books.stream()
        .map(livro->livro.author())
        .toList());
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        String auxAuthor = author.toLowerCase();
        return books.stream().filter(book -> book.author().toLowerCase().equals(auxAuthor)).toList();
    }

    @Override
    public List<Book> getBooksByAuthorAndYear(String author, int year) {
        String auxAuthor = author.toLowerCase();
        return books.stream().filter(book -> book.author().toLowerCase().equals(auxAuthor) && book.yearAux() == year).toList();
    }

    @Override
    public boolean addBook(Book book) {
        return books.add(book);
    }

}
