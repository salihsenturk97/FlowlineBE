package com.flowline.ws.dto.auth;

import com.flowline.ws.dto.user.UserDTO;
import lombok.Data;

@Data
public class AuthResponse {
    UserDTO user;
    Token token;
}
