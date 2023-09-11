package com.dgliger.repository;

import com.dgliger.model.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, String> {
    PasswordHistory findByUserId(String id);
}
