package com.example.MyBookShopApp.data;

public class Author {
  private Integer id;
  private String name;
  private String biographyShort;
  private String biographyRest;
  private String photo;

  public Author(Integer id, String name, String biographyShort, String biographyRest, String photo) {
    this.id = id;
    this.name = name;
    this.biographyShort = biographyShort;
    this.biographyRest = biographyRest;
    this.photo = photo;
  }

  public Author() {
  }

  @Override
  public String toString() {
    return "Author{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", biographyShort='" + biographyShort + '\'' +
           ", biographyRest='" + biographyRest + '\'' +
           ", photo='" + photo + '\'' +
           '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBiographyShort() {
    return biographyShort;
  }

  public void setBiographyShort(String biographyShort) {
    this.biographyShort = biographyShort;
  }

  public String getBiographyRest() {
    return biographyRest;
  }

  public void setBiographyRest(String biographyRest) {
    this.biographyRest = biographyRest;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
}
