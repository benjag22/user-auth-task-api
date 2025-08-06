package com.example.auth_task_api.persistence.repository;

import com.example.auth_task_api.persistence.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
