package com.bcopstein.demo1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BookCollectionJDBC implements IBookCollection{
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookCollectionJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LinkedList<Book> getAllBooks() {
        List<Book> resp = this.jdbcTemplate.query("SELECT * from books",
            (rs, rowNum) -> 
                new Book(rs.getLong("identifier"), rs.getString("title"), rs.getString("author"), rs.getInt("yearAux")));
        return new LinkedList<Book>(resp);
    }

    @Override
    public List<String> getAllBookTitles() {
        List<String> resp = this.jdbcTemplate.query("SELECT DISTINCT title from books",
            (rs, rowNum) -> 
                rs.getString("title"));
        return resp;
    }

    @Override
    public Set<String> getAllBookAuthors() {
        List<String> resp = this.jdbcTemplate.query("SELECT DISTINCT author from books",
            (rs, rowNum) -> 
                rs.getString("author"));
        return new HashSet<String>(resp);
    }


    @Override
    public List<Book> getBooksByAuthor(String author) {
        List<Book> resp = jdbcTemplate.query(
                "SELECT * FROM books WHERE author = "+author,
                (rs, rowNum) -> new Book(rs.getLong("identifier"), rs.getString("title"), rs.getString("author"), rs.getInt("yearAux")));
        return resp;
    }

    @Override
    public List<Book> getBooksByAuthorAndYear(String author, int year) {
        
        List<Book> resp = jdbcTemplate.query(
                "SELECT DISTINCT * FROM books WHERE author = ? AND yearAux = ?",
                (rs, rowNum) -> new Book(rs.getLong("identifier"), rs.getString("title"), rs.getString("author"), rs.getInt("yearAux")),
                author, year);
        return resp;
    }


    @Override
    public boolean addBook(Book book) {
        this.jdbcTemplate.update("INSERT INTO books (identifier, title, author, yearAux) VALUES (?,?,?,?)", book.identifier(), book.title(), book.author(), book.yearAux());
        return true;
    }

}
