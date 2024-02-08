package com.flowline.ws.business;

import com.flowline.ws.dto.auth.AuthResponse;
import com.flowline.ws.dto.auth.Credentials;

public interface AuthService {

    AuthResponse authenticate(Credentials creds);
}
