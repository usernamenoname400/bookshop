package com.example.MyBookShopApp.data;

public class AlphabetLink {
  private Character character;
  private String link;

  public AlphabetLink() {
  }

  public AlphabetLink(Character character, String link) {
    this.character = character;
    this.link = link;
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
}
