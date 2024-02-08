package com.flowline.ws.business;

import com.flowline.ws.entites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    void save(User user);

    void activateUser(String token);

    Page<User> inquireUsers(Pageable pageable);

    User getUserById(long userId);

    User findByEmail(String email);
}
