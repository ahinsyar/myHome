package com.myhome.server.service;

import com.myhome.server.domain.User;
import com.myhome.server.domain.enumvalue.Role;
import com.myhome.server.dto.UserRequestDto;
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
import org.springframework.data.domain.*;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
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

    List<User> userList = new ArrayList<>();
    Page<User> userPage;

    @BeforeEach
    void beforeEach() throws Exception {
        LocalDate birthDay1 = LocalDate.of(1985, 11, 5);
        LocalDate birthDay2 = LocalDate.of(1985, 5, 2);
        LocalDate birthDay3 = LocalDate.of(1985, 1, 8);

        testUser1 = new User(1L, "박순영", "ethan", "1234", Role.PARENTS, birthDay1, "ethan@home.com", "010-5443-8549");
        testUser2 = new User(2L, "이유진", "pluto", "1234", Role.PARENTS, birthDay2, "pluto@home.com", "010-7744-4321");
        testUser3 = new User(3L, "박철영", "volts", "1234", Role.UNCLE, birthDay3, "volts@home.com", "010-4387-1771");

        userList.add(testUser1);
        userList.add(testUser2);
        userList.add(testUser3);
        userPage = new PageImpl<>(userList);
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
        assertThat(findUser.getName()).isEqualTo(testUser1.getName());

    }

    @Test
    @DisplayName("유저 리스트 확인")
    void findUserListTest_Success() throws Exception
    {
        Pageable pageable = PageRequest.of(0, 3, Sort.by("idx"));
        //given
        given(userRepository.findAll(pageable)).willReturn(userPage);
        //when
        Page<UserResponseDto> users = userService.findUsers(pageable);

        //then
        assertThat(users.getContent()).hasSize(3);
        for(int i = 0; i<users.getContent().size(); i++){
            assertThat(users.getContent().get(i).getName()).isEqualTo(userList.get(i).getName());
            assertThat(users.getContent().get(i).getRole()).isEqualTo(userList.get(i).getRole());
        }
    }

    @Test
    @DisplayName("유저 저장 확인")
    void saveUserTest_Success() throws Exception
    {
        LocalDate birthday = LocalDate.of(1956, 7, 18);
        User saveTestUser = new User(4L,"박상권", "park111", "1234", Role.GRANDPARENTS, birthday, "park111@home.com", "010-9885-5515");
        UserRequestDto req =  new UserRequestDto("박상권", "park111", "1234", Role.GRANDPARENTS, birthday, "park111@home.com", "010-9885-5515");
        //given
        given(userRepository.save(any())).willReturn(saveTestUser);

        //when
        UserResponseDto res = userService.saveUser(req);

        //then
        assertThat(res.getIdx()).isEqualTo(saveTestUser.getIdx());
        assertThat(res.getName()).isEqualTo(saveTestUser.getName());

    }

    @Test
    @DisplayName("유저 저장하고 리스트 확인")
    void saveUserAndFindUsersTest_Success() throws Exception
    {
        //given
        LocalDate birthday = LocalDate.of(1956, 7, 18);
        User saveTestUser = new User(4L,"박상권", "park111", "1234", Role.GRANDPARENTS, birthday, "park111@home.com", "010-9885-5515");
        UserRequestDto req =  new UserRequestDto("박상권", "park111", "1234", Role.GRANDPARENTS, birthday, "park111@home.com", "010-9885-5515");
        userList.add(saveTestUser);
        userPage = new PageImpl<>(userList);

        Pageable pageable = PageRequest.of(0, 3, Sort.by("idx"));


        given(userRepository.save(any())).willReturn(saveTestUser);
        given(userRepository.findAll(pageable)).willReturn(userPage);
        //when
        UserResponseDto res = userService.saveUser(req);
        Page<UserResponseDto> users = userService.findUsers(pageable);

        //then
        assertThat(res.getIdx()).isEqualTo(saveTestUser.getIdx());
        assertThat(res.getName()).isEqualTo(saveTestUser.getName());
        assertThat(users.getContent()).hasSize(4);
    }

    @Test
    @DisplayName("이미 등록된 유저를 재 등록")
    void saveUserTest_Fail() throws Exception
    {
        LocalDate birthDay1 = LocalDate.of(1985, 11, 5);
        User saveTestUser = new User(1L, "박순영", "ethan", "1234", Role.PARENTS, birthDay1, "ethan@home.com", "010-5443-8549");
        UserRequestDto req =  new UserRequestDto( "박순영", "ethan", "1234", Role.PARENTS, birthDay1, "ethan@home.com", "010-5443-8549");
        //given
        given(userRepository.existsByUserId(anyString())).willReturn(true);

        //when
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    userService.saveUser(req);
                });

        //then
        assertThat(exception.getMessage()).isEqualTo("이미 등록된 유저입니다.");
    }

}