package com.flowline.ws.business;

import com.flowline.ws.dto.auth.AuthResponse;
import com.flowline.ws.dto.auth.Credentials;
import com.flowline.ws.core.exceptions.AuthenticationException;
import com.flowline.ws.dto.auth.Token;
import com.flowline.ws.entites.User;
import com.flowline.ws.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private TokenService tokenService;
    @Override
    public AuthResponse authenticate(Credentials creds) {
        User inDb = this.userService.findByEmail(creds.email());
        if(inDb==null) throw new AuthenticationException();
        if(!this.passwordEncoder.matches(creds.password(),inDb.getPassword())) throw new AuthenticationException();

        Token token = this.tokenService.createToken(inDb, creds);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserDTO(inDb));
            return authResponse ;

    }
}
