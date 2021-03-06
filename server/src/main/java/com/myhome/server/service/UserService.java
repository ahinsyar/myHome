package com.myhome.server.service;

import com.myhome.server.domain.User;
import com.myhome.server.dto.UserRequestDto;
import com.myhome.server.dto.UserResponseDto;
import com.myhome.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponseDto findUser(Long idx){
        User findUser = userRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));
        return UserResponseDto.toDto(findUser);
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDto> findUsers(Pageable pageable){
        return userRepository.findAll(pageable).map(UserResponseDto::toDto);
    }

    public UserResponseDto saveUser(UserRequestDto request){
        existUser(request.getUserId());
        User requestUser = request.toEntity();
        User savedUser = userRepository.save(requestUser);

        return UserResponseDto.toDto(savedUser);
    }

    public UserResponseDto updateUser(UserRequestDto request){
        User findUser = userRepository.findById(request.getIdx()).orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));
        User update = findUser.update(request.toEntity());
        return UserResponseDto.toDto(update);
    }

    public void deleteUser(UserRequestDto request) {
        User findUser = userRepository.findById(request.getIdx()).orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));
        findUser.update(request.toEntity());
    }

    private void existUser(String userId) {
        if(userRepository.existsByUserId(userId)){
            throw new IllegalArgumentException("이미 등록된 유저입니다.");
        }
    }
}
