package com.example.servlet;

import com.example.jsp.paths.JSPPath;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private final Logger logger = Logger.getGlobal();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        try {
            response.sendRedirect(JSPPath.LOGIN);
        } catch (IOException exception) {
            logger.severe(exception.getMessage());
        }
    }
}
