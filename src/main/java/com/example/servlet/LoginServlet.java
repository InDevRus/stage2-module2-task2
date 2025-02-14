package com.example.servlet;

import com.example.Users;
import com.example.jsp.paths.JSPPath;

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
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final Users users = Users.getInstance();

    private final Logger logger = Logger.getGlobal();

    private boolean isPasswordBlankOrNull(ServletRequest request) {
        String password = request.getParameter("password");
        return Objects.isNull(password) || password.isEmpty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String targetJSP = Objects.nonNull(session.getAttribute("user")) ? JSPPath.USER_HELLO : JSPPath.LOGIN;
        try {
            response.sendRedirect(targetJSP);
        } catch (IOException exception) {
            logger.severe(exception.getMessage());
        }
    }

    private Optional<String> extractUserLogin(ServletRequest request) {
        return Optional.ofNullable(request.getParameter("login")).filter(users.getUsers()::contains);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Optional<String> user = extractUserLogin(request);
        boolean areAttributesCorrect = !isPasswordBlankOrNull(request) && user.isPresent();

        try {
            if (areAttributesCorrect) {
                session.setAttribute("user", user.get());
                response.sendRedirect(JSPPath.USER_HELLO);
            } else {
                request.getRequestDispatcher(JSPPath.LOGIN).forward(request, response);
            }
        } catch (ServletException | IOException exception) {
            logger.severe(exception.getMessage());
        }
    }
}
