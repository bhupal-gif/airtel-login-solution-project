package com.dgliger.services;

import com.dgliger.exceptions.BadRequestException;
import com.dgliger.exceptions.EntityNotFoundException;
import com.dgliger.mapper.UserMapper;
import com.dgliger.model.ERole;
import com.dgliger.model.PasswordHistory;
import com.dgliger.model.Role;
import com.dgliger.model.User;
import com.dgliger.model.request.ChangePasswordRequest;
import com.dgliger.model.request.UpdateUserRequest;
import com.dgliger.model.request.UserRequest;
import com.dgliger.model.response.ChangePasswordResponse;
import com.dgliger.model.response.UserResponse;
import com.dgliger.repository.PasswordHistoryRepository;
import com.dgliger.repository.RoleRepository;
import com.dgliger.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    public static final String NO_SUCH_USER_EXISTS = "No Such User Exists!!!";
    public static final String PASSWORD_CHANGED_SUCCESSFULLY = "Password changed successfully";
    public static final String USERNAME_IS_ALREADY_TAKEN = "Error: Username is already taken!";
    public static final String EMAIL_IS_ALREADY_TAKEN = "Error: Email is already in use!";
    private static final String ROLE_NOT_FOUND = "Role_Not_Found";
    private static final String INCORRECT_OLD_PASSWORD = "Incorrect_Old_Password";
    private static final String NEW_PASS_AND_OLD_PASS_DOES_NOT_MATCH = "Pass_Mismatch";
    private static final String NEW_PASS_AND_OLD_PASS_CANNOT_BE_SAME = "New pass and old pass cannot be same!!";
    private static final String PASSWORD_FORMAT = "Pass_Format";
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final LoginSettingService loginSettingService;
    private final RoleRepository roleRepository;
    private final PasswordHistoryRepository passwordHistoryRepository;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, LoginSettingService loginSettingService, RoleRepository roleRepository, PasswordHistoryRepository passwordHistoryRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.loginSettingService = loginSettingService;
        this.roleRepository = roleRepository;
        this.passwordHistoryRepository = passwordHistoryRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUserName(userRequest.getUserName()))) {
            throw new BadRequestException(USERNAME_IS_ALREADY_TAKEN);
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(userRequest.getEmail()))) {
            throw new BadRequestException(EMAIL_IS_ALREADY_TAKEN);
        }

        Set<String> strRoles = userRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER.name()).orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
            roles.add(userRole);
        } else {
            strRoles.forEach(
                    role -> {
                        switch (role) {
                            case "admin":
                                Role adminRole = roleRepository.findByName(ERole.ADMIN.name()).orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
                                roles.add(adminRole);
                                break;
                            case "moderator":
                                Role modRole = roleRepository.findByName(ERole.MODERATOR.name()).orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
                                roles.add(modRole);
                                break;
                            default:
                                Role userRole = roleRepository.findByName(ERole.USER.name()).orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
                                roles.add(userRole);
                        }
                    });
        }
        return UserMapper.mapUserToUserResponse(userRepository.save(UserMapper.mapUserRequestToUser(userRequest, roles)));
    }

    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserMapper::mapUserToUserResponse);
    }

    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NO_SUCH_USER_EXISTS));
        return UserMapper.mapUserToUserResponse(user);
    }

    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new EntityNotFoundException(NO_SUCH_USER_EXISTS));
    }

    public UserResponse updateUser(UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(updateUserRequest.getId()).orElseThrow(() -> new EntityNotFoundException(NO_SUCH_USER_EXISTS));
        return UserMapper.mapUserToUserResponse(userRepository.save(UserMapper.mapUpdateUserRequestToUser(user, updateUserRequest)));
    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest request, String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new EntityNotFoundException(NO_SUCH_USER_EXISTS));
        if (!encoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new EntityNotFoundException(INCORRECT_OLD_PASSWORD);
        }
        if (request.getNewPassword().equals(request.getOldPassword())) {
            throw new EntityNotFoundException(NEW_PASS_AND_OLD_PASS_CANNOT_BE_SAME);
        }
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new EntityNotFoundException(NEW_PASS_AND_OLD_PASS_DOES_NOT_MATCH);
        }
        userRepository.save(user.toBuilder().password(encoder.encode(request.getNewPassword())).build());
        Optional<PasswordHistory> optionalPasswordHistory = passwordHistoryRepository.findById(user.getId());
        if (optionalPasswordHistory.isPresent()) {
            updatePasswordHistory(optionalPasswordHistory.get(), request.getNewPassword());
        } else {
            passwordHistoryRepository.save(PasswordHistory.builder().userId(user.getId()).firstPassword(encoder.encode(request.getNewPassword())).build());
        }
        return ChangePasswordResponse.builder().message(PASSWORD_CHANGED_SUCCESSFULLY).build();
    }

    public void updatePasswordHistory(PasswordHistory passwordHistory, String newPassword) {
        if (passwordHistory.getFirstPassword() != null) {
            if (passwordHistory.getSecondPassword() != null) {
                if (passwordHistory.getThirdPassword() != null) {
                    if (passwordHistory.getFourthPassword() != null) {
                        passwordHistory.setFifthPassword(passwordHistory.getFourthPassword());
                    }
                    passwordHistory.setFourthPassword(passwordHistory.getThirdPassword());
                }
                passwordHistory.setThirdPassword(passwordHistory.getSecondPassword());
            }
            passwordHistory.setSecondPassword(passwordHistory.getFirstPassword());
        }
        passwordHistory.setFirstPassword(encoder.encode(newPassword));
        passwordHistoryRepository.save(passwordHistory);
    }

}
