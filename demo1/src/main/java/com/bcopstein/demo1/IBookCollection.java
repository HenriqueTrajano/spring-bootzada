package com.bcopstein.demo1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public interface IBookCollection {
    LinkedList<Book> getAllBooks();
    List<String> getAllBookTitles();
    Set<String> getAllBookAuthors();
    List<Book> getBooksByAuthor(String author);
    List<Book> getBooksByAuthorAndYear(String author, int year);
    boolean addBook(Book book);
}
