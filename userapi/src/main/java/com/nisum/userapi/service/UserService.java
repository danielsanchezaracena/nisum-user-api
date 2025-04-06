package com.nisum.userapi.service;

import com.nisum.userapi.dto.UserRequestDTO;
import com.nisum.userapi.dto.UserResponseDTO;
import com.nisum.userapi.entity.Phone;
import com.nisum.userapi.entity.User;
import com.nisum.userapi.repository.UserRepository;
import com.nisum.userapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
public class UserService {

    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @Value("${validation.password.regex}")
    private String passwordRegex;

    @Value("${email.validation.regex}")
    private String emailRegex;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDTO saveUser(UserRequestDTO request){

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The email is already registered");
        }

        if(!Pattern.matches(passwordRegex,request.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid password format");
        }

        if(!Pattern.matches(emailRegex,request.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid email format");
        }

        User user=new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setActive(true);
        user.setCreationDate(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(jwtUtil.generateToken(request.getEmail()));

        user.setPhones(request.getPhones().stream().map(p->{
            Phone phone = new Phone();
            phone.setNumber(p.getNumber());
            phone.setCityCode(p.getCitycode());
            phone.setCountryCode(p.getCountrycode());
            return phone;
        }).toList());

        User savedUser=userRepository.save(user);

        return new UserResponseDTO(savedUser.getId(),
                savedUser.getCreationDate(), savedUser.getModified(),
                savedUser.getLastLogin(), savedUser.getToken(), savedUser.isActive());
    }
}
