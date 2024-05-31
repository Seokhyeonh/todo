package io.thesun4sky.todoapp.service;

import io.thesun4sky.todoapp.dto.SignupRequestDto;

import io.thesun4sky.todoapp.entity.User;
import io.thesun4sky.todoapp.entity.UserRoleEnum;
import io.thesun4sky.todoapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signUp(SignupRequestDto userDto) {
        // username 중복 확인
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }

        // username, password 유효성 검사
        if (!isValidUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("사용자 이름은 4자 이상, 10자 이하의 알파벳 소문자와 숫자로 이루어져야 합니다.");
        }

        if (!isValidPassword(userDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호는 8자 이상, 15자 이하의 알파벳 대소문자와 숫자로 이루어져야 합니다.");
        }

        // UserDto를 User 엔티티로 변환하여 저장
        User user = new User(userDto.getNickname(), userDto.getUsername(), userDto.getPassword(),UserRoleEnum.valueOf(userDto.getRole()) );
        user.setUserId(userDto.getUserId());
        user.setNickname(userDto.getNickname());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(UserRoleEnum.valueOf(userDto.getRole()));


        userRepository.save(user);
    }

    // username 유효성 검사
    private boolean isValidUsername(String username) {
        return username.matches("^[a-z0-9]{4,10}$");
    }

    // password 유효성 검사
    private boolean isValidPassword(String password) {
        return password.matches("^[a-zA-Z0-9]{8,15}$");
    }
}