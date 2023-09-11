package com.dgliger.services;

import com.dgliger.exceptions.EntityNotFoundException;
import com.dgliger.mapper.LoginSettingMapper;
import com.dgliger.model.LoginSetting;
import com.dgliger.model.request.LoginSettingRequest;
import com.dgliger.model.request.UpdateLoginSettingRequest;
import com.dgliger.model.response.LoginSettingResponse;
import com.dgliger.repository.LoginSettingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LoginSettingService {
    private static final String ENTITY_NOT_FOUND = "Entity Not Found";

    private final LoginSettingRepository loginSettingRepository;

    public LoginSettingService(LoginSettingRepository loginSettingRepository) {
        this.loginSettingRepository = loginSettingRepository;
    }

    public LoginSetting getByParameterName(String name) {
        return loginSettingRepository.findByParameterName(name);
    }

    public LoginSettingResponse createLoginSetting(LoginSettingRequest request) {
        return LoginSettingMapper.mapLoginSettingToLoginSettingResponse(
                loginSettingRepository.save(
                        LoginSettingMapper.mapLoginSettingRequestToLoginSetting(request)));
    }

    public LoginSettingResponse updateLoginSetting(UpdateLoginSettingRequest request) {
        LoginSetting loginSetting =
                loginSettingRepository
                        .findById(request.getId())
                        .orElseThrow(
                                () -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        return LoginSettingMapper.mapLoginSettingToLoginSettingResponse(
                loginSettingRepository.save(
                        LoginSettingMapper.mapUpdateLoginSettingRequestToLoginSetting(request, loginSetting)));
    }

    public Page<LoginSettingResponse> getAllLoginSetting(Pageable pageable) {
        return loginSettingRepository
                .findAll(pageable)
                .map(LoginSettingMapper::mapLoginSettingToLoginSettingResponse);
    }

    public LoginSettingResponse getLoginSettingById(String id) {
        LoginSetting loginSetting =
                loginSettingRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        return LoginSettingMapper.mapLoginSettingToLoginSettingResponse(loginSetting);
    }
}
