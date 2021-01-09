package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public BookService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Book> getBooksData() {
    List<Book> books =
        jdbcTemplate.query(
            "select b.id, a.name author, b.title, b.priceOld, b.price " +
            "from books b " +
            "join authors a on a.id = b.author_id",
            (ResultSet rs, int rowNum) ->
               new Book(
                   rs.getInt("id"),
                   rs.getString("author"),
                   rs.getString("title"),
                   rs.getString("priceOld"),
                   rs.getString("price")
               )
        );
    return new ArrayList<>(books);
  }

  public List<Book> getBooksByAuthor(Integer authorId) {
    List<Book> books =
        jdbcTemplate.query(
            "select b.id, a.name author, b.title, b.priceOld, b.price " +
            "from books b " +
            "join authors a on a.id = b.author_id " +
            "where b.author_id = ?",
            (ResultSet rs, int rowNum) ->
                new Book(
                    rs.getInt("id"),
                    rs.getString("author"),
                    rs.getString("title"),
                    rs.getString("priceOld"),
                    rs.getString("price")
                ),
            new Object[]{authorId}
        );
    return new ArrayList<>(books);
  }
}
