package ru.mirea.task23.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

    @GetMapping("/home")
    public ModelAndView getHomePage() {
        return new ModelAndView("home.html");
    }

//    @GetMapping("/")
//    public String indexPage() {
//        return "index";
//    }
}
