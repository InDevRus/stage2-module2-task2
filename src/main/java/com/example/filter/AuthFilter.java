package com.example.filter;

import com.example.jsp.paths.JSPPath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

@WebFilter(urlPatterns = "/user/*")
public class AuthFilter implements Filter {
    private final Logger logger = Logger.getGlobal();

    @Override
    public void init(FilterConfig filterConfig) {
        //Required to be implemented by Tomcat
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        if (!(servletRequest instanceof HttpServletRequest)) {
            logger.severe("Request must implement HttpServletRequest");
            return;
        }

        if (!(servletResponse instanceof HttpServletResponse)) {
            logger.severe("Response must implement HttpServletResponse");
            return;
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        boolean isUserAbsent = Objects.isNull(httpServletRequest.getSession().getAttribute("user"));

        try {
            if (isUserAbsent) {
                httpServletResponse.sendRedirect(JSPPath.LOGIN);
                return;
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServletException exception) {
            logger.severe(exception.getMessage());
        }
    }
}