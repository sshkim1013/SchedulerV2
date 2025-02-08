package com.example.schedulerv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")  //H2 데이터베이스로 테스트 시 테이블명 겹침(H2 DB 내부적으로 USER 테이블이 존재).
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;


    public User(String name, String email) {
        this.name = name;
        this.email = email;
        createDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
    }

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
        modifiedDate = LocalDateTime.now();
    }
}
