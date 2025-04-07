package com.nisum.userapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private String token;
    private boolean isactive;
    private List<PhoneDTO> phones;

}
