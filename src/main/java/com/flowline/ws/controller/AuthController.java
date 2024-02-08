package com.flowline.ws.controller;

import com.flowline.ws.business.AuthService;
import com.flowline.ws.dto.auth.AuthResponse;
import com.flowline.ws.dto.auth.Credentials;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AuthController {
@Autowired
    private AuthService authService;

    @PostMapping("/api/v1/auth")
    AuthResponse handleAuthentication(@Valid @RequestBody Credentials creds){
       return this.authService.authenticate(creds);
    }


}
