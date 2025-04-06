package com.nisum.userapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class UserResponseDTO {

    private UUID userId;
    private String name;
    private String email;
    private LocalDateTime creationDate;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
    private List<PhoneDTO> phones;


    public UserResponseDTO(UUID userId, LocalDateTime creationDate, LocalDateTime modified, LocalDateTime lastLogin, String token, boolean isActive) {
        this.userId = userId;
        this.creationDate = creationDate;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }
}
