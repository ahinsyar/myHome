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

    @Builder
    public User(String name, String userId, String password, Role role, LocalDate birthday, String email, String phoneNumber) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User update(User updateUser) {

        if(updateUser.getPassword() != null){
            password = updateUser.getPassword();
        }
        if(updateUser.getName() != null){
            name = updateUser.getName();
        }
        if(updateUser.getRole() != null){
            role = updateUser.getRole();
        }
        if(updateUser.getBirthday() != null){
            birthday = updateUser.getBirthday();
        }
        if(updateUser.getEmail() != null){
            email = updateUser.getEmail();
        }
        if(updateUser.getPhoneNumber() != null){
            phoneNumber = updateUser.getPhoneNumber();
        }

        return this;
    }
}
