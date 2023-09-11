package com.dgliger.repository;

import com.dgliger.model.LoginSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginSettingRepository extends JpaRepository<LoginSetting, String> {

    LoginSetting findByParameterName(String name);
}
