package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {

  @GetMapping("/")
  public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
    return new ModelAndView("redirect:/bookshop/main", model);
  }
}
