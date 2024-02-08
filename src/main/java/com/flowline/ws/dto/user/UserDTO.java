package com.flowline.ws.dto.user;

import com.flowline.ws.entites.User;
import lombok.Data;

@Data
public class UserDTO {

    private long id;

    private String username;

    private String email;

    private String image;

    public UserDTO(User user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setUsername(user.getUsername());
        setImage(user.getImage());
    }
}
