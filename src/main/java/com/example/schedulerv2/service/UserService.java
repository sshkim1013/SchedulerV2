package com.example.schedulerv2.service;

import com.example.schedulerv2.dto.user.UserRequestDto;
import com.example.schedulerv2.dto.user.UserResponseDto;
import com.example.schedulerv2.entity.User;
import com.example.schedulerv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto saveUser(UserRequestDto dto) {
        User user = new User(dto.getName(), dto.getEmail());
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    @Transactional
    public List<UserResponseDto> findAllUsers() {
        List<User> findUsers = userRepository.findAll();

        List<UserResponseDto> dtoList = new ArrayList<>();
        for (User findUser : findUsers) {
            dtoList.add(new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getEmail()));
        }

        return dtoList;
    }

    @Transactional
    public UserResponseDto findUserById(Long id) {
        User findUser = userRepository.findUserByIdOrElseThrow(id);

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getEmail());
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User findUser = userRepository.findUserByIdOrElseThrow(id);

        findUser.update(dto.getName(), dto.getEmail());

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getEmail());
    }

    @Transactional
    public void deleteUser(Long id) {
        User findUser = userRepository.findUserByIdOrElseThrow(id);

        userRepository.deleteById(findUser.getId());
    }
}
