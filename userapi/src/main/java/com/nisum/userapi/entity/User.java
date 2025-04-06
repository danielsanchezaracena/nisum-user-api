package com.nisum.userapi.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

    @Id
    private UUID id;

    private String name;
    private String email;
    private String password;
    private String token;
    private boolean isActive;
    private LocalDateTime creationDate;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL,orphanRemoval = true)
    private List<Phone> phones=new ArrayList<>();

}
