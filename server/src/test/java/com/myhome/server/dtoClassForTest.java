package com.myhome.server;

import com.myhome.server.domain.enumvalue.Role;
import com.myhome.server.dto.UserRequestDto;
import com.myhome.server.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class dtoClassForTest {
    public UserRequestDto userRequestDto1, userRequestDto2, userRequestDto3, userRequestDto4, userRequestDto5, userRequestDto6;
    public UserResponseDto userResponseDto1, userResponseDto2, userResponseDto3, userResponseDto4, userResponseDto5, userResponseDto6;

    public List<UserResponseDto> list = new ArrayList<>();
    public Page<UserResponseDto> page;

    public dtoClassForTest(){
        makeUserRequestDto();
        makeUserResponseDto();

        setList();
        page = new PageImpl<>(list);
    }

    private void setList() {
        list.add(userResponseDto1);
        list.add(userResponseDto2);
        list.add(userResponseDto3);
        list.add(userResponseDto4);
        list.add(userResponseDto5);
        list.add(userResponseDto6);

    }

    private void makeUserResponseDto() {
        userResponseDto1 = UserResponseDto.builder()
                .idx(1L)
                .name("박순영")
                .userId("ahinsyar")
                .role(Role.ADMIN)
                .email("ahinsyar@gmail.com")
                .phoneNumber("01054438549")
                .password("1234")
                .birthday(LocalDate.of(1985, 11, 6))
                .build();

        userResponseDto2 = UserResponseDto.builder()
                .idx(2L)
                .name("이유진")
                .userId("zinna")
                .role(Role.PARENTS)
                .email("zinna@gmail.com")
                .phoneNumber("01077444212")
                .password("1234")
                .birthday(LocalDate.of(1985, 5, 3))
                .build();

        userResponseDto3 = UserResponseDto.builder()
                .idx(3L)
                .name("박상권")
                .userId("spark")
                .role(Role.GRANDPARENTS)
                .email("spark@gmail.com")
                .phoneNumber("01098555515")
                .password("1234")
                .birthday(LocalDate.of(1956, 7, 18))
                .build();

        userResponseDto4 = UserResponseDto.builder()
                .idx(4L)
                .name("강순옥")
                .userId("skang")
                .role(Role.GRANDPARENTS)
                .email("skang@gmail.com")
                .phoneNumber("01095461106")
                .password("1234")
                .birthday(LocalDate.of(1960, 7, 18))
                .build();

        userResponseDto5 = UserResponseDto.builder()
                .idx(5L)
                .name("박서윤")
                .userId("seoyun")
                .role(Role.CHILDREN)
                .email("seoyun@gmail.com")
                .phoneNumber("")
                .password("1234")
                .birthday(LocalDate.of(2016, 8, 27))
                .build();

        userResponseDto6 = UserResponseDto.builder()
                .idx(6L)
                .name("박서희")
                .userId("seohui")
                .role(Role.CHILDREN)
                .email("seohui@gmail.com")
                .phoneNumber("")
                .password("1234")
                .birthday(LocalDate.of(2018, 11, 7))
                .build();
    }

    private void makeUserRequestDto() {
        userRequestDto1 = UserRequestDto.builder()
                .name("박순영")
                .userId("ahinsyar")
                .role(Role.ADMIN)
                .email("ahinsyar@gmail.com")
                .phoneNumber("01054438549")
                .password("1234")
                .birthday(LocalDate.of(1985, 11, 6))
                .build();

        userRequestDto2 = UserRequestDto.builder()
                .name("이유진")
                .userId("zinna")
                .role(Role.PARENTS)
                .email("zinna@gmail.com")
                .phoneNumber("01077444212")
                .password("1234")
                .birthday(LocalDate.of(1985, 5, 3))
                .build();

        userRequestDto3 = UserRequestDto.builder()
                .name("박상권")
                .userId("spark")
                .role(Role.GRANDPARENTS)
                .email("spark@gmail.com")
                .phoneNumber("01098555515")
                .password("1234")
                .birthday(LocalDate.of(1956, 7, 18))
                .build();

        userRequestDto4 = UserRequestDto.builder()
                .name("강순옥")
                .userId("skang")
                .role(Role.GRANDPARENTS)
                .email("skang@gmail.com")
                .phoneNumber("01095461106")
                .password("1234")
                .birthday(LocalDate.of(1960, 7, 18))
                .build();

        userRequestDto5 = UserRequestDto.builder()
                .name("박서윤")
                .userId("seoyun")
                .role(Role.CHILDREN)
                .email("seoyun@gmail.com")
                .phoneNumber("")
                .password("1234")
                .birthday(LocalDate.of(2016, 8, 27))
                .build();

        userRequestDto6 = UserRequestDto.builder()
                .name("박서희")
                .userId("seohui")
                .role(Role.CHILDREN)
                .email("seohui@gmail.com")
                .phoneNumber("")
                .password("1234")
                .birthday(LocalDate.of(2018, 11, 7))
                .build();
    }
}
