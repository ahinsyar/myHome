package com.myhome.server.dto;

import com.myhome.server.domain.User;
import com.myhome.server.domain.enumvalue.Role;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@Builder
public class UserResponseDto {
    private Long idx;

    private String name;

    private String userId;

    private String password;

    private Role role;

    private LocalDate birthday;

    @Email
    private String email;

    private String phoneNumber;

    public static UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .idx(user.getIdx())
                .name(user.getName())
                .userId(user.getUserId())
                .password(user.getPassword())
                .role(user.getRole())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();

    }
}
