package com.example.filter;

import com.example.jsp.paths.JSPPath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = "/user/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        //Required to be implemented by Tomcat
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest)) {
            throw new ServletException("Request must implement HttpServletRequest");
        }

        if (!(servletResponse instanceof HttpServletResponse)) {
            throw new ServletException("Response must implement HttpServletResponse");
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (Objects.isNull(httpServletRequest.getSession().getAttribute("user"))) {
            httpServletResponse.sendRedirect(JSPPath.LOGIN.getPathToJSP());
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}