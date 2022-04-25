package ru.mirea.task23.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.mirea.task23.handlers.ApiError;
import ru.mirea.task23.models.dto.UserLoginDTO;
import ru.mirea.task23.models.dto.UserRegistrationDTO;
import ru.mirea.task23.security.response.MessageEntity;
import ru.mirea.task23.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-auth")
public class AuthController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public ResponseEntity<MessageEntity> authenticate(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            service.login(userLoginDTO);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new MessageEntity(ex.getMessage()));
        }

        return ResponseEntity.ok(new MessageEntity("Login successful"));
    }

    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            service.register(userRegistrationDTO);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new MessageEntity(ex.getMessage()));
        } catch (IllegalStateException ex) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

        return ResponseEntity.ok(new MessageEntity("Registration successful"));
    }
}
