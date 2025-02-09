package com.example.schedulerv2.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private final Long id;
    private final String name;
    private final String email;
}
