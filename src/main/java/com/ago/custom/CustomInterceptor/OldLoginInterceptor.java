package com.ago.custom.CustomInterceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:OldLoginInterceprtor
 * @Describe:
 * @Data:2020/5/2614:36
 * @Author:Ago
 * @Version 1.0
 */
@Component
public class OldLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        System.out.println("get in to OldLoginInterceptor ------------");
        System.out.println("this login url" + request.getRequestURI() + "+is abandonment , redirect new Api url");
        response.sendRedirect(request.getContextPath() + "/interceptor/newLogin");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("do postHandle ------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("afterCompletion ----");
        Long endTime = System.currentTimeMillis();
        System.out.println("接口用时 :" + (endTime - startTime));
    }
}
