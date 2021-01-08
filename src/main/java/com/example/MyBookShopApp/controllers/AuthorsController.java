package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.LocalProperties;
import com.example.MyBookShopApp.data.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/${local.basePath}")
public class AuthorsController {
  private final AuthorService authorService;
  private final LocalProperties localProperties;

  public AuthorsController(AuthorService authorService, LocalProperties localProperties) {
    this.authorService = authorService;
    this.localProperties = localProperties;
  }

  @GetMapping("/${local.authorsPage}")
  public String getGenres(Model model) {
    model.addAttribute("authorGroups", authorService.getAuthorGroups());
    model.addAttribute("authorData", authorService.getAuthorsInGroups());
    model.addAttribute("mainPage", localProperties.getMainPageFullPath());
    model.addAttribute("genresPage", localProperties.getGenresPageFullPath());
    model.addAttribute("authorsPage", localProperties.getAuthorsPageFullPath());
    return "authors/index";
  }
}
