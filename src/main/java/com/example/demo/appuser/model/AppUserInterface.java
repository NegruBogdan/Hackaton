package com.example.demo.appuser.model;

import com.example.demo.login.LoginRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AppUserInterface {
    boolean saved(String email, String password);

    AppUser getByEmailAndPassword(String email, String password);
    List<AppUser> getAll();
}
