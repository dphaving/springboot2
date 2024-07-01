package org.tad.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName TokenInterceptor
 * @Description TokenInterceptor
 * @Author Tad
 * @Date 2024/5/27 22:50
 * @Version 1.0
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)) {
            log.info("验证token...");
            return true;
        } else {
            log.info("未携带token...");
            return false;
        }
    }
}
