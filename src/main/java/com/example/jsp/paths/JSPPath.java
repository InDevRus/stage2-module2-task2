package com.example.jsp.paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;


public enum JSPPath {
    USER_HELLO("/user/hello.jsp"),
    @SuppressWarnings("unused")
    INDEX("/index.jsp"),
    LOGIN("/login.jsp");

    private final String pathToJSP;

    JSPPath(String pathToJSP) {
        this.pathToJSP = pathToJSP;
    }

    public String getPathToJSP() {
        return pathToJSP;
    }

    @SuppressWarnings("unused")
    public String encodeRedirectURLWith(HttpServletRequest request, HttpServletResponse response) {
        return response.encodeRedirectURL(MessageFormat.format("{0}{1}",
                request.getContextPath(),
                pathToJSP));
    }
}
