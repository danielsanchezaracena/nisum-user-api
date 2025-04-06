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
}
