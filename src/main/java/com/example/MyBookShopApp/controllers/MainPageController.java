package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.LocalProperties;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/${local.basePath}")
public class MainPageController {
  private final BookService bookService;
  private final LocalProperties localProperties;

  @Autowired
  public MainPageController(BookService bookService, LocalProperties localProperties) {
    this.bookService = bookService;
    this.localProperties = localProperties;
  }

  @GetMapping("/${local.mainPage}")
  public String mainPage(Model model) {
    model.addAttribute("bookData", bookService.getBooksData());
    model.addAttribute("mainPage", localProperties.getMainPageFullPath());
    model.addAttribute("genresPage", localProperties.getGenresPageFullPath());
    model.addAttribute("authorsPage", localProperties.getAuthorsPageFullPath());
    return "index";
  }
}
