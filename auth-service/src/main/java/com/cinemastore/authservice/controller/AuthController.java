package com.cinemastore.authservice.controller;

import com.cinemastore.authservice.controller.helpers.AuthEndpoints;
import com.cinemastore.authservice.dto.UserRequestDto;
import com.cinemastore.authservice.dto.UserResponseDto;
import com.cinemastore.authservice.exception.NoSuchRoleException;
import com.cinemastore.authservice.exception.UserAlreadyExistsException;
import com.cinemastore.authservice.service.implementation.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(AuthEndpoints.AUTH)
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    public AuthController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping(AuthEndpoints.SIGN_UP)
    public ResponseEntity<UserResponseDto> registry(@Valid @RequestBody UserRequestDto userRequestDto) throws NoSuchRoleException, UserAlreadyExistsException {
        if (userServiceImpl.userExistsByUsername(userRequestDto.getUsername())) {
            throw new UserAlreadyExistsException("User with such username already exists");
        }
        if (userServiceImpl.userExistsByEmail(userRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("User with such email already exists");
        }
        return new ResponseEntity<>(userServiceImpl.save(userRequestDto), HttpStatus.CREATED);
    }
}
