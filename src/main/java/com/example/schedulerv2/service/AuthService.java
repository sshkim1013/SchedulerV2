package com.example.schedulerv2.service;

import com.example.schedulerv2.dto.login.LoginRequestDto;
import com.example.schedulerv2.dto.user.UserResponseDto;
import com.example.schedulerv2.entity.User;
import com.example.schedulerv2.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public UserResponseDto login(LoginRequestDto dto,
                                 HttpServletRequest request,
                                 HttpServletResponse response
    ) {
        User findUser = userRepository.findUserByEmailOrElseThrow(dto.getEmail());

        // 비밀번호 비교
        if (!findUser.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일과 비밀번호가 일치하지 않습니다.");
        }

        // 세션이 없으면 새로 생성하고, 있으면 기존 세션을 사용
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);  // 세션이 없으면 새로 생성
        }

        // 로그인에 성공한 사용자의 세션에 대한 추가 로직
        session.setAttribute("sessionKey", findUser.getEmail());

        // 세션 ID를 쿠키에 담아 클라이언트에 전달
        Cookie sessionCookie = new Cookie("sessionId", session.getId());
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setMaxAge(3600); // 1시간 유지
        response.addCookie(sessionCookie);

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getPassword());
    }

    public void logout(HttpServletRequest request,  HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }

        // 쿠키 제거
        Cookie sessionCookie = new Cookie("sessionId", null);
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(0);
        response.addCookie(sessionCookie);
    }
}
