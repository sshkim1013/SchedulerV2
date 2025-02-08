package com.example.schedulerv2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Schedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String title;

    private String content;

    private LocalDateTime createDate;       //jpa auditing 활용

    private LocalDateTime modifiedDate;     //jpa auditing 활용

    public Schedule(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
        createDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        modifiedDate = LocalDateTime.now(); //일정 수정 -> 수정 시간 갱신.
    }
}
