package com.bcopstein.demo1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BookCollectionJDBC implements IBookCollection{
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookCollectionJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public LinkedList<Book> getAllBooks() {
        List<Book> resp = this.jdbcTemplate.query("SELECT * from books",
            (rs, rowNum) -> 
                new Book(rs.getLong("indentifier"), rs.getString("title"), rs.getString("author"), rs.getInt("year")));
        return new LinkedList<Book>(resp);
    }

    public List<String> getAllBookTitles() {
        List<String> resp = this.jdbcTemplate.query("SELECT DISTINCT title from books",
            (rs, rowNum) -> 
                rs.getString("title"));
        return resp;
    }

    public Set<String> getAllBookAuthors() {
        List<String> resp = this.jdbcTemplate.query("SELECT DISTINCT author from books",
            (rs, rowNum) -> 
                rs.getString("author"));
        return new HashSet<String>(resp);
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> resp = jdbcTemplate.query(
                "SELECT DISTINCT * FROM books WHERE author = "+author,
                (rs, rowNum) -> new Book(rs.getLong("indentifier"), rs.getString("title"), rs.getString("author"), rs.getInt("year")));
        return resp;
    }

    public List<Book> getBooksByAuthorAndYear(String author, int year) {
        List<Book> resp = jdbcTemplate.query(
                "SELECT DISTINCT * FROM books WHERE author = "+author+"AND year = "+year,
                (rs, rowNum) -> new Book(rs.getLong("indentifier"), rs.getString("title"), rs.getString("author"), rs.getInt("year")));
        return resp;
    }

    public boolean addBook(Book book) {
        this.jdbcTemplate.update("INSERT INTO books (identifier, title, author, year) VALUES (?,?,?,?)", book.identifier(), book.title(), book.author(), book.year());
        return true;
    }

    

    //FAZER MAIS AQUI
}
