package com.example.auth_task_api.persistence.model;

import com.example.auth_task_api.persistence.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "task")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eliminated")
    private Boolean eliminated = Boolean.FALSE;

    @Column(name = "tittle", nullable = false, length = 64)
    private String tittle;

    @Column(name = "description", length = 256)
    private String description;

    @Column(name = "expiration_date", columnDefinition  = "DATE DEFAULT (CURRENT_DATE + INTERVAL '2 month')")
    private LocalDate expirationDate;

    @Column(name = "creation_date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDateTime creationDate;

    @Column(name = "status")
    private TaskStatus status = TaskStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

}
