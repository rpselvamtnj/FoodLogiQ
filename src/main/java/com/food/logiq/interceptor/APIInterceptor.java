package com.food.logiq.interceptor;

import com.food.logiq.restapi.users.UserService;
import com.food.logiq.util.ErrorCodes;
import com.food.logiq.util.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor class for intercept all http call for validate the request.
 * Annotated with {@link Component @Component}, and {@link Slf4j @Slf4j}.
 *
 * @author Rotation5
 */
@Slf4j
@Component
public class APIInterceptor implements HandlerInterceptor {

    /**
     * Instance variables
     */
    private final UserService userService;

    /**
     * Create a new {@code ApiInterceptor} with the given parameters class instance.
     *
     * @param userService the {@link UserService @UserService} class instance
     */
    public APIInterceptor(UserService userService) {
        this.userService = userService;
    }

    /**
     * prehandle method for validate the http request.
     *
     * @param request  the {@link HttpServletRequest @HttpServletRequest}.
     * @param response the {@link HttpServletResponse @HttpServletResponse}.
     * @param handler  the {@link Object @Object}.
     * @return the {@link Boolean @Boolean}.
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {

        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            throw new UnauthorizedException(ErrorCodes.TOKEN_NOT_FOUND);
        }
        log.info("Authorization :{}", authorization);
        String token = authorization.contains("Bearer ") ? authorization.replace("Bearer ", "") : authorization;
        if (StringUtils.isEmpty(token)) {
            throw new UnauthorizedException(ErrorCodes.TOKEN_NOT_FOUND);
        }

        String userId = request.getHeader("userId");
        if (StringUtils.isEmpty(userId)) {
            throw new UnauthorizedException(ErrorCodes.INVALID_USER);
        }
        boolean isValidUser = userService.isValidUser(userId, token);
        if (!isValidUser) {
            throw new UnauthorizedException(ErrorCodes.INVALID_USER_TOKEN);
        }
        log.info("#### Valid user ####");
        return true;
    }
}
