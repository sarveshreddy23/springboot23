package com.learning.springboot.entity;

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
public class User {
    @Id
    @SequenceGenerator(
            name = "userId",
            sequenceName = "userId"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userId"
    )
    private Long id;
    @Column(name = "user_name")
    private String username;
    private String password;
    private String role;
}
