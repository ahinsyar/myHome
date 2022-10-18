package com.myhome.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myhome.server.domain.User;
import com.myhome.server.domain.enumvalue.Role;
import com.myhome.server.dto.UserRequestDto;
import com.myhome.server.dto.UserResponseDto;
import com.myhome.server.repository.UserRepository;
import com.myhome.server.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    UserRequestDto request;
    UserResponseDto response, responseList1, responseList2, responseList3;

    @BeforeEach
    void before(){
        request = UserRequestDto.builder()
                .name("박순영")
                .userId("ethan")
                .role(Role.ADMIN)
                .email("ethan.park@jeju-semi.com")
                .phoneNumber("01054438549")
                .password("1234")
                .birthday(LocalDate.of(1985, 11, 6))
                .build();

        response = UserResponseDto.builder()
                .idx(1L)
                .name("박순영")
                .userId("ethan")
                .role(Role.ADMIN)
                .email("ethan.park@jeju-semi.com")
                .phoneNumber("01054438549")
                .password("1234")
                .birthday(LocalDate.of(1985, 11, 6))
                .build();
    }

    @Test
    @DisplayName("유저 생성")
    void addUserTest() throws Exception
    {
        //given
        String requestContent = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/user/add")
                    .content(requestContent)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());


        //when

        //then

    }

    @Test
    @DisplayName("유저 찾기")
    void findUserTest() throws Exception
    {
        //given
        given(userService.findUser(anyLong())).willReturn(response);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(8)))
                .andExpect(jsonPath("$.name", is(request.getName())))
                .andExpect(jsonPath("$.userId", is(request.getUserId())))
                .andDo(print());
        //when

        //then

    }
}