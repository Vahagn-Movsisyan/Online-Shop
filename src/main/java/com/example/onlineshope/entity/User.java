package com.example.onlineshope.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String picName;

    @Column(name = "confirm_email_code")
    private String emailConfirmCode;
    private boolean active;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
