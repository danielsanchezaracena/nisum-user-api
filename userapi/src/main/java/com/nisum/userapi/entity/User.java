package com.nisum.userapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "myuser")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;
    private String password;
    private String token;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL,orphanRemoval = true)
    private List<Phone> phones=new ArrayList<>();

}
