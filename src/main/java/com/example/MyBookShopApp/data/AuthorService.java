package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.LocalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
  private final JdbcTemplate jdbcTemplate;
  private final List<String> images;
  private final LocalProperties localProperties;
  private final ResourceLoader resourceLoader;

  @Autowired
  public AuthorService(JdbcTemplate jdbcTemplate, LocalProperties localProperties, ResourceLoader resourceLoader) {
    this.localProperties = localProperties;
    this.resourceLoader = resourceLoader;
    this.jdbcTemplate = jdbcTemplate;
    this.images =
        jdbcTemplate.query(
            "select distinct photo from authors",
            (ResultSet rs, int rowNum) -> rs.getString("photo")
        );
  }

  public List<Author> getAuthorsData() {
    List<Author> authors =
        jdbcTemplate.query(
            "select * from authors order by name",
            (ResultSet rs, int rowNum) ->
            new Author(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("biography_short"),
                rs.getString("biography_rest"),
                rs.getString("photo")
            )
        );

    return new ArrayList<>(authors);
  }

  public Author getAuthorData(Integer authorId) {
    Author author =
        jdbcTemplate.queryForObject(
            "select * from authors where id = ?",
             (ResultSet rs, int rowNum) ->
               new Author(
                   rs.getInt("id"),
                   rs.getString("name"),
                   rs.getString("biography_short"),
                   rs.getString("biography_rest"),
                   rs.getString("photo")
               ),
            new Object[]{authorId}
        );

    return author;
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
            "select a.id, a.name, a.biography_short, a.biography_rest, a.photo, substr(a.name, 1, 1) group_name" +
            " from authors a order by a.name"
        );
    while (rs.next()) {
      if (rs.getString("group_name").charAt(0) != group) {
        if (tempResult != null) {
          result.add(new AuthorsGroup(group, tempResult));
        }
        tempResult = new ArrayList<>();
        group = rs.getString("group_name").charAt(0);
      }
      tempResult.add(
          new Author(
              rs.getInt("id"),
              rs.getString("name"),
              rs.getString("biography_short"),
              rs.getString("biography_rest"),
              rs.getString("photo")
          )
      );
    }
    if (tempResult != null && !tempResult.isEmpty()) {
      result.add(new AuthorsGroup(group, tempResult));
    }

    return result;
  }

  public boolean isPhotoExists(String photo) {
    return this.images.contains(photo);
  }

  public Resource getImageFile(String photo) {
    if (this.images.contains(photo)) {
      return new ClassPathResource(localProperties.getImagesPath() + File.separator + photo);
    } else {
      return new ClassPathResource(localProperties.getPlaceholderPath() + File.separator + "author.jpg");
    }
  }
}
