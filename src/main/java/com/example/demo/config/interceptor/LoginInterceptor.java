package com.example.demo.config.interceptor;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Component
public class LoginInterceptor implements HandlerInterceptor{

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                              Object handler) throws Exception{
        System.out.println("LoginInterceptor.preHandle()");
        HttpSession session = request.getSession();
        if (session.getAttribute("name")==null){
            System.out.println("Session中未获取到数据");
            response.sendRedirect("/html/login.html");
            return false;
        }else {
            return true;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception{
        System.out.println("LoginInterceptor.postHandler()");
    }

    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,
                                Object handler) throws Exception{
        System.out.println("LoginInterceptor.afterCompletion()");
    }

    /**
     * public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
     * 			ModelAndView modelAndView) throws Exception {
     * 		System.out.println("LoginInterceptor.postHandle()");
     *        }
     *
     * 	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
     * 			throws Exception {
     * 		System.out.println("LoginInterceptor.afterCompletion()");
     *    }
     */
}
