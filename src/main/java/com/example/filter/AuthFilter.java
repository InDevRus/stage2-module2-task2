package com.example.filter;

import com.example.jsp.paths.JSPPaths;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = "/user/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        if (Objects.isNull(servletRequest.getAttribute("user"))) {
            servletRequest.getRequestDispatcher(JSPPaths.LOGIN).forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}