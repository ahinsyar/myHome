package com.myhome.server.service;

import com.myhome.server.domain.User;
import com.myhome.server.domain.enumvalue.Role;
import com.myhome.server.dto.UserResponseDto;
import com.myhome.server.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@Transactional
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User testUser1, testUser2, testUser3;
    UserResponseDto testDto1, testDto2, testDto3;

    @BeforeEach
    void beforeEach() throws Exception {
        LocalDate birthDay1 = LocalDate.of(1985, 11, 5);
        LocalDate birthDay2 = LocalDate.of(1985, 5, 2);
        LocalDate birthDay3 = LocalDate.of(1985, 1, 8);

        testUser1 = new User(1L, "박순영", "ethan", "1234", Role.PARENTS, birthDay1, "ethan@home.com", "010-5443-8549");
        testUser2 = new User(2L, "이유진", "pluto", "1234", Role.PARENTS, birthDay2, "pluto@home.com", "010-7744-4321");
        testUser3 = new User(3L, "박철영", "volts", "1234", Role.UNCLE, birthDay3, "volts@home.com", "010-4387-1771");
    }

    @AfterEach
    void afterEach() throws Exception {

    }

    @Test
    @DisplayName("유저 찾기")
    void findUserTest_Success() throws Exception
    {
        //given
        given(userRepository.findById(anyLong())).willReturn(Optional.of(testUser1));
        //when
        UserResponseDto findUser = userService.findUser(1L);

        //then
        Assertions.assertThat(findUser.getName()).isEqualTo(testUser1.getName());

    }

}