package com.github.w4o.sa.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.w4o.sa.commons.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.w4o.sa.commons.CommonResult.PASS_ERR;

/**
 * @author frank
 * @date 2019-05-14
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String result;
        ObjectMapper objectMapper = new ObjectMapper();
        if (authException instanceof UsernameNotFoundException) {
            result = objectMapper.writeValueAsString(new CommonResult().failed(PASS_ERR, "用户名或密码错误"));
        } else {
            result = objectMapper.writeValueAsString(new CommonResult().failed(-1, "未授权？"));
        }
        response.getWriter().println(result);
        response.getWriter().flush();
    }

}
