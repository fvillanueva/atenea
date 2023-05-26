package com.fvilla.services.authservice.controllers;

import com.fvilla.services.authservice.dtos.UserCredentialDTO;
import com.fvilla.services.authservice.services.UserCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserCredentialsController {

    private final UserCredentialService userCredentialService;

    @PostMapping("/register")
    public String register(@RequestBody UserCredentialDTO userCredential) {
        return userCredentialService.register(userCredential);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody UserCredentialDTO userCredential) {
        return userCredentialService.authenticate(userCredential);
    }

}
