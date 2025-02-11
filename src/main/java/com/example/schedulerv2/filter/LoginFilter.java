package com.example.schedulerv2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/auth/login", "/users", "/users/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행! 요청 URI={}", requestURI);

        //WHITE_LIST 내에 포함된 경우 true -> !true -> false
        if (!isWhiteList(requestURI)) {

            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute("sessionKey") == null) {
                log.info("인증 실패! 로그인 필요.");
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
                return;
            }

            log.info("인증 성공! 세션 확인 완료.");

        }

        //1번 경우 : WHITE_LIST에 등록된 URL 요청이라면, 바로 filterChain.doFilter() 호출.
        //2번 경우 : WHITE_LIST가 아닌 경우, 위 필터 로직을 통과한 후에 filterChain.doFilter() 다음 서블릿 또는 필터를 호출.
        //다음 필터가 없으면 servlet -> controller, 다음 필터가 있으면 다음 Filter 호출.
        filterChain.doFilter(request, response);

    }

    //WHITE_LIST 배열의 값과 일치하는지 검증하는 로직
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
