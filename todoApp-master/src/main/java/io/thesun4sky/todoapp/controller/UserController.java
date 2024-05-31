package io.thesun4sky.todoapp.controller;

import io.thesun4sky.todoapp.CommonResponse;
import io.thesun4sky.todoapp.dto.SignupRequestDto;
import io.thesun4sky.todoapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<String>> signUp(@Validated @RequestBody SignupRequestDto userDto) {
        userService.signUp(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse.<String>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .msg("회원가입이 완료되었습니다.")
                        .build());
    }
}