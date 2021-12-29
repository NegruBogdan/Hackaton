package com.example.demo.login;

import com.example.demo.appuser.model.AppUser;
import com.example.demo.appuser.model.AppUserInterface;
import com.example.demo.appuser.service.AppUserService;
import com.example.demo.registration.RegistrationService;
import com.example.demo.security.AppUserSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@AllArgsConstructor
public class LoginController {

    @Autowired
    AppUserSession appUserSession;

    @Autowired
    AppUserService appUserService;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login.html");
    }

//    private final LoginService loginService;
//
//    @PostMapping
//    public String login(@RequestBody LoginRequest request)
//    {
//        return loginService.login(request);
//    }

    @PostMapping(value="/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postLogin(LoginRequest data) {
        ModelAndView loginView = new ModelAndView("login.html");

        AppUser user = appUserService.getByEmailAndPassword(data.getEmail(), data.getPassword());

        if(user == null){
            loginView.addObject("errLogin", "Please insert valid email and password");
        } else {
            appUserSession.setId(user.getId());
            return new ModelAndView("redirect:/register");
        }



        return loginView;
    }

}
