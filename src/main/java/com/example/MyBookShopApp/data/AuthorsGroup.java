package com.example.MyBookShopApp.data;

import java.util.List;

public class AuthorsGroup {
  private Character group;
  private List<Author> authors;

  public Character getGroup() {
    return group;
  }

  public void setGroup(Character group) {
    this.group = group;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public AuthorsGroup(Character group, List<Author> authors) {
    this.group = group;
    this.authors = authors;
  }
}
