package com.dgliger.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoginResponse {
    private String token;
    private String id;
    private String userName;
    private String email;
    private String loggedInTime;
    private List<String> roles;
}
