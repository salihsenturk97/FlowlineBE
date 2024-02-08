package com.flowline.ws.dto.user;

import com.flowline.ws.entites.User;
import com.flowline.ws.core.validation.UniqueEmail;
import jakarta.validation.constraints.*;

public record UserCreate(
        @NotNull
        @NotBlank(message = "{flowline.constraint.username.notblank.message}")
        @Size(min = 4, max = 255)
        String username,

        @NotNull
        @NotBlank
        @Email(message = "{flowline.constraint.email.well.formed.message}")
        @UniqueEmail(message = "{flowline.constraint.email.notunique.message}")
        String email,

        @Size(min = 8, max = 255, message = "{flowline.constraint.password.size.message}")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{flowline.constraint.password.pattern.message}")
        String password
) {

    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
}
