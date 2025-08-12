package com.example.auth_task_api.persistence.repository;

import com.example.auth_task_api.persistence.model.UserLoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginDataRepository extends JpaRepository<UserLoginData, Long> {

    boolean existsByNickname(String nickname);

    boolean existsByEmailAddress(String emailAddress);

}
