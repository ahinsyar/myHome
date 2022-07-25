package com.myhome.server.dto;

import com.myhome.server.domain.User;
import com.myhome.server.domain.enumvalue.Role;
import lombok.*;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public UserRequestDto(Long idx, Role role) {
        this.idx = idx;
        this.role = role;
    }

    @Builder
    public UserRequestDto(String name, String userId, String password, Role role, LocalDate birthday, String email, String phoneNumber) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

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
