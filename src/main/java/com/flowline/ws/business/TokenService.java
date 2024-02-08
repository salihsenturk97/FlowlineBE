package com.flowline.ws.business;

import com.flowline.ws.dto.auth.Credentials;
import com.flowline.ws.dto.auth.Token;
import com.flowline.ws.entites.User;

public interface TokenService {

    public Token createToken(User user, Credentials credentials);

    public User verifyToken(String authorizationHeader);

}
