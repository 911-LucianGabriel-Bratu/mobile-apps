package com.example.appserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userID;

    private String username;
    private String userPassword;
}
