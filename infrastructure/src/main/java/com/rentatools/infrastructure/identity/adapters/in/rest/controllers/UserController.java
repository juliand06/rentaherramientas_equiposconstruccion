package com.rentatools.infrastructure.identity.adapters.in.rest.controllers;

import com.rentatools.application.identity.command.RegisterUserCommand;
import com.rentatools.application.identity.dto.UserDto;
import com.rentatools.application.identity.usecase.GetUserProfileUseCase;
import com.rentatools.application.identity.usecase.RegisterUserUseCase;
import com.rentatools.infrastructure.identity.adapters.in.rest.dtos.RegisterUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RegisterUserUseCase registerUser;
    private final GetUserProfileUseCase getUserProfile;

    public UserController(RegisterUserUseCase registerUser, GetUserProfileUseCase getUserProfile) {
        this.registerUser = registerUser;
        this.getUserProfile = getUserProfile;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@Valid @RequestBody RegisterUserRequest request) {
        return registerUser.execute(new RegisterUserCommand(
                request.email(),
                request.password(),
                request.firstName(),
                request.lastName(),
                request.role()
        ));
    }

    @GetMapping("/{id}")
    public UserDto getProfile(@PathVariable UUID id) {
        return getUserProfile.execute(id);
    }
}
