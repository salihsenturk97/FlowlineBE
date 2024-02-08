package com.flowline.ws.business;

import com.flowline.ws.dto.auth.Credentials;
import com.flowline.ws.dto.auth.Token;
import com.flowline.ws.entites.User;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class BasicAuthTokenService implements TokenService{

    @Override
    public Token createToken(User user, Credentials credentials) {
        String emailColonPassword = credentials.email()+":"+credentials.password();

        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());
        return new Token("Basic",token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }


}
