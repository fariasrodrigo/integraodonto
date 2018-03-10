package br.com.integraodonto.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
            throws Exception {

        String uri = request.getRequestURI();
        if (uri.endsWith("login")
                || uri.endsWith("criando-consultorio")
                || uri.endsWith("adiciona-consultorio")
                || uri.endsWith("acessando-consultorio")
                || uri.contains("resources")) {
            return true;
        }

        if (request.getSession().getAttribute("logando") != null) {
            return true;
        } else {
            response.sendRedirect("login");
            return false;
        }
    }
}
