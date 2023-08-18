package com.example.servlet;

import com.example.Users;
import com.example.jsp.paths.JSPPaths;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final Users users = Users.getInstance();

    private boolean isPasswordBlankOrNull(ServletRequest request) {
        String password = request.getParameter("password");
        return Objects.isNull(password) || password.isEmpty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String targetJSP = Objects.nonNull(session.getAttribute("user")) ? JSPPaths.HELLO : JSPPaths.LOGIN;
        request.getRequestDispatcher(targetJSP).forward(request, response);
    }

    private Optional<String> extractUserLogin(ServletRequest session) {
        return Optional.ofNullable(session.getParameter("login")).filter(users.getUsers()::contains);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Optional<String> user = extractUserLogin(request);
        boolean areAttributesCorrect = isPasswordBlankOrNull(request) && user.isPresent();

        String targetJSP = areAttributesCorrect ? JSPPaths.HELLO : JSPPaths.LOGIN;
        if (areAttributesCorrect) {
            session.setAttribute("user", user.get());
        }
        request.getRequestDispatcher(targetJSP).forward(request, response);
    }
}
