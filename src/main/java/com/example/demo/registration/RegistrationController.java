package com.example.demo.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @PostMapping
    public String register(@ModelAttribute RegistrationRequest request) {
        return registrationService.register(request);
    }

}
