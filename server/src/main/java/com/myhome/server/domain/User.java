package com.myhome.server.domain;

import com.myhome.server.domain.enumvalue.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;

    private String userId;

    private String password;

    private Role role;

    private LocalDate birthday;

    @Email
    private String email;

    private String phoneNumber;



}
