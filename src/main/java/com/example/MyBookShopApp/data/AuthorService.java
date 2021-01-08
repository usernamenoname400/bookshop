package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public AuthorService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Author> getAuthorsData() {
    List<Author> authors =
        jdbcTemplate.query("select a.id, a.name from authors a order by a.name",
                           (ResultSet rs, int rowNum) -> {
              Author author = new Author();
              author.setId(rs.getInt("id"));
              author.setName(rs.getString("name"));
              return author;
            });

    return new ArrayList<>(authors);
  }

  public List<AlphabetLink> getAuthorGroups() {
    List<Character> groups =
      jdbcTemplate.query(
          "select distinct upper(substr(a.name, 1, 1)) group_name from authors a",
          (ResultSet rs, int rowNum) -> rs.getString("group_name").charAt(0));

    List <AlphabetLink> result = new ArrayList<>();
    for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
      if (groups.contains(alphabet)) {
        result.add(new AlphabetLink(alphabet, "#" + alphabet));
      } else {
        result.add(new AlphabetLink(alphabet, ""));
      }
    }

    return result;
  }

  public List<AuthorsGroup> getAuthorsInGroups() {
    List<AuthorsGroup> result = new ArrayList();
    List<Author> tempResult = null;
    Character group = '-';

    SqlRowSet rs =
        jdbcTemplate.queryForRowSet(
            "select a.id, a.name, substr(a.name, 1, 1) group_name from authors a order by a.name"
        );
    while (rs.next()) {
      if (rs.getString("group_name").charAt(0) != group) {
        if (tempResult != null) {
          result.add(new AuthorsGroup(group, tempResult));
        }
        tempResult = new ArrayList<>();
        group = rs.getString("group_name").charAt(0);
      }
      Author author = new Author();
      author.setId(rs.getInt("id"));
      author.setName(rs.getString("name"));
      tempResult.add(author);
    }
    if (tempResult != null && !tempResult.isEmpty()) {
      result.add(new AuthorsGroup(group, tempResult));
    }

    return result;
  }
}
