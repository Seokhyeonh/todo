package io.thesun4sky.todoapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private Long userId;

    private String nickname;

    private String username;

    private String password;

    private String role;

    private String createdAt;
}
