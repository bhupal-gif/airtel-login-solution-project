package com.dgliger.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ChangePasswordRequest {
    @NotBlank
    @Size(max = 20)
    private String oldPassword;
    @NotBlank
    @Size(max = 20)
    private String newPassword;
    @NotBlank
    @Size(max = 20)
    private String confirmPassword;
}
