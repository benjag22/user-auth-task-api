package com.example.auth_task_api.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@Table(name = "user_login_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserLoginData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname", length = 100, unique = true, nullable = false)
    private String nickname;

    @Column(name = "session_token", length = 86, unique = true)
    private String sessionToken;

    @Column(name = "password_salt", length = 43, nullable = false)
    private String passwordSalt;

    @Column(name = "password_hash", length = 63, nullable = false)
    private String passwordHash;

    @Column(name = "email_adress", length = 254, nullable = false, unique = true)
    private String emailAdress;

    @Column(name = "confirmation_token", length = 86)
    private String confirmationToken;

    @Column(name = "token_generation_time")
    private Timestamp tokenGenerationTime = null;

    @Column(name = "password_recovery_time")
    private Timestamp passwordRecoveryTime = null;

    @Column(name = "recovery_token_time")
    private Timestamp recoveryTokenTime = null;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

}
