package com.myhome.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myhome.server.dto.UserRequestDto;
import com.myhome.server.dto.UserResponseDto;
import com.myhome.server.dtoClassForTest;
import com.myhome.server.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
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

    dtoClassForTest dtoClassForTest = new dtoClassForTest();
    UserRequestDto request;
    UserResponseDto response;

    List<UserResponseDto> list = new ArrayList<>();
    Page<UserResponseDto> page;

    @BeforeEach
    void before(){
        request = dtoClassForTest.userRequestDto1;

        response = dtoClassForTest.userResponseDto1;

        list = dtoClassForTest.list;
        page = new PageImpl<>(list);
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

    @Test
    @DisplayName("유저 List")
    void findUsersTest() throws Exception
    {
        //given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("idx"));
        page = new PageImpl<>(list, pageRequest, list.size());

        given(userService.findUsers(pageRequest)).willReturn(page);
        //when
        mockMvc.perform(get("/users")
                .param("page", "0")
                .param("size", "3")
                .param("sort", "idx,ASC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages", is(2)))
                .andExpect(jsonPath("$.last", is(false)))
                .andExpect(jsonPath("$.content[0].name", is(dtoClassForTest.userRequestDto1.getName())))
                .andExpect(jsonPath("$.content[1].name", is(dtoClassForTest.userRequestDto2.getName())))
                .andExpect(jsonPath("$.content[2].name", is(dtoClassForTest.userRequestDto3.getName())))
                .andDo(print());

        //then

    }

    @Test
    @DisplayName("유저 수정하기")
    void updateUserTest() throws Exception
    {
        //given
        UserResponseDto responseUser1 = dtoClassForTest.userResponseDto1;
        UserResponseDto response = UserResponseDto.builder()
                .idx(responseUser1.getIdx())
                .name(responseUser1.getName())
                .password("2345678")
                .role(responseUser1.getRole())
                .birthday(responseUser1.getBirthday())
                .email(responseUser1.getEmail())
                .phoneNumber(responseUser1.getPhoneNumber())
                .build();

        given(userService.updateUser(any())).willReturn(response);

        UserRequestDto request = new UserRequestDto(responseUser1.getName(), responseUser1.getUserId(), "2345678", responseUser1.getRole(), responseUser1.getBirthday(), responseUser1.getEmail(), responseUser1.getPhoneNumber());

        String requestContent = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(post("/user/update")
                .content(requestContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(request.getName())))
                .andExpect(jsonPath("$.password", is(request.getPassword())))
                .andDo(print());

        //then

    }
}