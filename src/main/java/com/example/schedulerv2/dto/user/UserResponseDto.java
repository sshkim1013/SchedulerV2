package com.example.schedulerv2.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    private final LocalDateTime createDate;

    private final LocalDateTime modifiedDate;
}
