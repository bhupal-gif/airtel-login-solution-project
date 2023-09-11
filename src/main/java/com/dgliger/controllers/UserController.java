package com.dgliger.controllers;


import com.dgliger.model.request.ChangePasswordRequest;
import com.dgliger.model.request.UpdateUserRequest;
import com.dgliger.model.request.UserRequest;
import com.dgliger.model.response.ChangePasswordResponse;
import com.dgliger.model.response.UserResponse;
import com.dgliger.services.AuthService;
import com.dgliger.services.UserService;
import com.dgliger.utility.CurrentUserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/users")
@Api(value = "User Controller")
@SecurityRequirement(name = "ALS")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/id/{id}")
    public UserResponse getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @PutMapping
    public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    @PutMapping("/change-password")
    public ChangePasswordResponse changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return userService.changePassword(changePasswordRequest, CurrentUserService.getLoggedUserName());
    }
}
