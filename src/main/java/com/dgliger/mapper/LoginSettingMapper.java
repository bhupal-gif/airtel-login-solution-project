package com.dgliger.mapper;

import com.dgliger.model.LoginSetting;
import com.dgliger.model.request.LoginSettingRequest;
import com.dgliger.model.request.UpdateLoginSettingRequest;
import com.dgliger.model.response.LoginSettingResponse;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class LoginSettingMapper {
    public LoginSetting mapLoginSettingRequestToLoginSetting(LoginSettingRequest request) {
        return LoginSetting.builder()
                .id(UUID.randomUUID().toString())
                .parameterName(request.getParameterName())
                .parameterValue(request.getParameterValue())
                .description(request.getDescription())
                .build();
    }

    public LoginSettingResponse mapLoginSettingToLoginSettingResponse(LoginSetting loginSetting) {
        return LoginSettingResponse.builder()
                .id(loginSetting.getId())
                .parameterName(loginSetting.getParameterName())
                .parameterValue(loginSetting.getParameterValue())
                .description(loginSetting.getDescription())
                .createdBy(loginSetting.getCreatedBy())
                .createdDate(loginSetting.getCreatedDate())
                .lastModifiedBy(loginSetting.getLastModifiedBy())
                .lastModifiedDate(loginSetting.getLastModifiedDate())
                .build();
    }

    public LoginSetting mapUpdateLoginSettingRequestToLoginSetting(
            UpdateLoginSettingRequest updateLoginSettingRequest, LoginSetting loginSetting) {
        return loginSetting.toBuilder()
                .parameterName(updateLoginSettingRequest.getParameterName())
                .parameterValue(updateLoginSettingRequest.getParameterValue())
                .description(updateLoginSettingRequest.getDescription())
                .createdBy(loginSetting.getCreatedBy())
                .createdDate(loginSetting.getCreatedDate())
                .build();
    }
}
