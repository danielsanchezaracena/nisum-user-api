package com.nisum.userapi.service;

import com.nisum.userapi.dto.PhoneDTO;
import com.nisum.userapi.dto.UserRequestDTO;
import com.nisum.userapi.dto.UserResponseDTO;
import com.nisum.userapi.entity.Phone;
import com.nisum.userapi.entity.User;
import com.nisum.userapi.repository.UserRepository;
import com.nisum.userapi.utils.JwtUtil;
import com.nisum.userapi.utils.UserValidationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserValidationUtils userUtils;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil,UserValidationUtils userUtils) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userUtils=userUtils;
    }

    public UserResponseDTO saveUser(UserRequestDTO request){

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The email is already registered.");
        }

        if(!userUtils.isValidPasswordFormat(request.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid password format.");
        }

        if(!userUtils.isValidEmailFormat(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid email format.");
        }

        User user=new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setIsactive(true);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(jwtUtil.generateToken(request.getEmail()));
        user.setPhones(request.getPhones().stream().map(p->{
            Phone phone = new Phone();
            phone.setNumber(p.getNumber());
            phone.setCitycode(p.getCitycode());
            phone.setCountrycode(p.getCountrycode());
            return phone;
        }).toList());

        User savedUser=userRepository.save(user);

         List<PhoneDTO> phonesDTO=savedUser.getPhones().stream().map(p->{
             PhoneDTO phoneDTO=new PhoneDTO();
             phoneDTO.setNumber(p.getNumber());
             phoneDTO.setCitycode(p.getCitycode());
             phoneDTO.setCountrycode(p.getCountrycode());
             return phoneDTO;
         }).toList();

        return new UserResponseDTO(savedUser.getId(),
                savedUser.getName(), savedUser.getEmail(),
                savedUser.getCreated(), savedUser.getModified(),
                savedUser.getLastLogin(), savedUser.getToken(), savedUser.isIsactive(),phonesDTO);
    }
}
