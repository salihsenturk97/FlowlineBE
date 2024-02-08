package com.flowline.ws.controller;

import com.flowline.ws.business.UserService;
import com.flowline.ws.core.shared.GenericMessage;
import com.flowline.ws.core.shared.Messages;
import com.flowline.ws.dto.user.UserCreate;
import com.flowline.ws.dto.user.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/v1/users")
    public GenericMessage createUser(@Valid @RequestBody UserCreate user) {
        this.userService.save(user.toUser());
        String message = Messages.getMessageForLocale( "flowline.create.user.success.message", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PatchMapping("/api/v1/users/{token}/active")
    public GenericMessage activeUser(@PathVariable String token) {
        this.userService.activateUser(token);
        String message = Messages.getMessageForLocale( "flowline.activate.user.success.message", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @GetMapping("/api/v1/users")
    public Page<UserDTO> getUsers(Pageable pageable) {
       return this.userService.inquireUsers(pageable).map(UserDTO::new);
    }

    @GetMapping("/api/v1/users/{userId}")
    public UserDTO getUserById(@PathVariable long userId){
        return new UserDTO(userService.getUserById(userId));
    }



}