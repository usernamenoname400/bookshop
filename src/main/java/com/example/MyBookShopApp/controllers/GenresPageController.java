package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.LocalProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/${local.basePath}")
public class GenresPageController {
  private final LocalProperties localProperties;

  public GenresPageController(LocalProperties localProperties) {
    this.localProperties = localProperties;
  }

  @GetMapping("/${local.genresPage}")
  public String getGenres(Model model) {
    model.addAttribute("mainPage", localProperties.getMainPageFullPath());
    model.addAttribute("genresPage", localProperties.getGenresPageFullPath());
    model.addAttribute("authorsPage", localProperties.getAuthorsPageFullPath());
    return "genres/index";
  }
}
