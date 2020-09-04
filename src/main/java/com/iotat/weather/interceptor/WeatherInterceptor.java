package com.iotat.weather.interceptor;

import com.iotat.weather.util.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class WeatherInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Headers,Authorization,token");
        response.setHeader("Access-Control-Max-Age", "1800");//30 min
        response.setContentType("application/json; charset=utf-8");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        System.out.println(longitude);
        System.out.println(latitude);
        if (longitude == null || latitude == null){
            System.out.println("参数为空或参数不全");
            response.getWriter().write(Result.resultStatus(400,"参数为空或参数不全"));
            return false;
        }
        return true;
    }
}
