package com.dgliger.mapper;

import com.dgliger.model.Role;
import com.dgliger.model.User;
import com.dgliger.model.request.UpdateUserRequest;
import com.dgliger.model.request.UserRequest;
import com.dgliger.model.response.UserResponse;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.UUID;

@UtilityClass
public class UserMapper {

    public User mapUserRequestToUser(UserRequest userRequest, Set<Role> roles) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .userName(userRequest.getUserName())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .roles(roles)
                .password("$2a$10$n8d3/59sunpWWHq6QlYkveL64wYqB6IWEqFqNhnMyqawccCGTc/dK")
                .build();
    }

    public UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdBy(user.getCreatedBy())
                .createdDate(user.getCreatedDate())
                .lastModifiedBy(user.getLastModifiedBy())
                .lastModifiedDate(user.getLastModifiedDate())
                .build();
    }

    public User mapUpdateUserRequestToUser(User user, UpdateUserRequest updateUserRequest) {
        return User.builder()
                .id(updateUserRequest.getId())
                .userName(updateUserRequest.getUserName().length() > 0 ? updateUserRequest.getUserName() : user.getUserName())
                .firstName(updateUserRequest.getFirstName().length() > 0 ? updateUserRequest.getFirstName() : user.getFirstName())
                .lastName(updateUserRequest.getLastName().length() > 0 ? updateUserRequest.getLastName() : user.getLastName())
                .email(updateUserRequest.getEmail().length() > 0 ? updateUserRequest.getEmail() : user.getEmail())
                .build();
    }

}
