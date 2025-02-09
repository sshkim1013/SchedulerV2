package com.example.schedulerv2.repository;

import com.example.schedulerv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);

    //특정 유저 존재 여부 검증 로직
    default User findUserByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 유저를 찾을 수 없습니다. 입력하신 ID = " + id));
    }

    default User findUserByNameOrElseThrow(String userName) {
        return findUserByName(userName).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 이름의 유저를 찾을 수 없습니다. 입력하신 이름 = " + userName));
    }
}
