package ru.mirea.task16.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomePageController {

    @GetMapping
    public ModelAndView getHomePage() {
        return new ModelAndView("home.html");
    }
}
