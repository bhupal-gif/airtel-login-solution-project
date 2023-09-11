package com.dgliger.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class LoginSettingRequest {
    @NotBlank
    private String parameterName;
    @NotBlank
    private String parameterValue;
    private String description;
}
