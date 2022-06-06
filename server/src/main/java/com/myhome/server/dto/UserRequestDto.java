package com.myhome.server.dto;

import com.myhome.server.domain.User;
import com.myhome.server.domain.enumvalue.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
public class UserRequestDto {

    private Long idx;

    private String name;

    private String userId;

    private String password;

    private Role role;

    private LocalDate birthday;

    @Email
    private String email;

    private String phoneNumber;

    public User toEntity() {
        return User.builder()
                .idx(this.idx)
                .name(this.name)
                .userId(this.userId)
                .password(this.password)
                .role(this.role)
                .birthday(this.birthday)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
