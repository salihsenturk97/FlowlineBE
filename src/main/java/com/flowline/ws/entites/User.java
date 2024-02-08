package com.flowline.ws.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Data
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean active = false;
    @JsonIgnore
    private String activationToken;

    private String image;
}
