package com.library.springboot.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.library.springboot.entity.Admin;
import com.library.springboot.exception.ServiceException;
import com.library.springboot.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    private static final String ERROR_CODE_401 = "401";

    @Autowired
    private IAdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ValidationException {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }

        // 执行认证
        if (StringUtils.isEmpty(token)) {
            throw new ServiceException(ERROR_CODE_401,"無token，請重新登錄");
        }
        // 获取 token 中的adminId
        String adminId;
        Admin admin;
        try {
            adminId = JWT.decode(token).getAudience().get(0);
            // 根据token中的userid查询数据库
            admin = adminService.getAdminById(Integer.parseInt(adminId));
        } catch (Exception e) {
            String errMsg = "token驗證失敗，請重新登錄";
            log.error(errMsg + ", token=" + token, e);
            throw new ServiceException(ERROR_CODE_401,errMsg);
        }
        if (admin == null) {
            throw new ServiceException(ERROR_CODE_401,"用戶不存在，請重新登錄");
        }

        try {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException(ERROR_CODE_401,"token驗證失敗，請重新登錄");
        }
        return true;
    }
}

