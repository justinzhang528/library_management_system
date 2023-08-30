package com.library.springboot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.library.springboot.entity.Admin;
import com.library.springboot.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Slf4j
public class TokenUtils {

    private static IAdminService staticAdminService;
    private static final long EXPIRATION_TIME = 5 * 60 * 1000; // minute * second * millisecond

    @Resource
    private IAdminService adminService;

    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String genToken(String adminId, String sign) {
        return JWT.create().withAudience(adminId) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 2小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return user对象
     *  /admin?token=xxxx
     */
    public static Admin getCurrentAdmin() {
        String token = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                token = request.getParameter("token");
            }
            if (StringUtils.isEmpty(token)) {
                log.error("獲取當前登錄的token失敗， token: {}", token);
                return null;
            }
            String adminId = JWT.decode(token).getAudience().get(0);
            return staticAdminService.getAdminById(Integer.valueOf(adminId));
        } catch (Exception e) {
            log.error("獲取當前登錄的管理員信息失敗, token={}", token,  e);
            return null;
        }
    }
}

