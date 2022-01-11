package com.example.demo;

import com.example.demo.appuser.model.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "")
public class HomeController {
    @GetMapping("")
    public String viewHomePage(Model model) {
        return "home";
    }
}
