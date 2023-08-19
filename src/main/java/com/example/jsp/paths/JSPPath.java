package com.example.jsp.paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

@SuppressWarnings("unused")
public final class JSPPath {
    public static final String USER_HELLO = "/user/hello.jsp";
    @SuppressWarnings("unused")
    public static final String INDEX = "/index.jsp";
    public static final String LOGIN = "/login.jsp";

    private JSPPath() {
    }

    @SuppressWarnings("unused")
    public static String encodeRedirectURLWith(HttpServletRequest request, HttpServletResponse response, String pathToJSP) {
        String resultingURL = MessageFormat.format("{0}{1}", request.getContextPath(), pathToJSP);
        return response.encodeRedirectURL(resultingURL);
    }
}
