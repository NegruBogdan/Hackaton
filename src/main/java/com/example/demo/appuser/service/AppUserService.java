package com.example.demo.appuser.service;

import com.example.demo.appuser.model.AppUser;
import com.example.demo.appuser.model.AppUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {


    AppUserInterface appUserInterface;


    public AppUser getByEmailAndPassword(String email, String password) {

        return appUserInterface.getByEmailAndPassword(email, password);
    }
}
