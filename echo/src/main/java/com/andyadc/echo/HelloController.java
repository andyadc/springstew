package com.andyadc.echo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public Object hello(HttpServletRequest request) {
        System.out.printf("ContextPath - %s%n", request.getContextPath());
        System.out.printf("PathInfo - %s%n", request.getPathInfo());
        System.out.printf("Method - %s%n", request.getMethod());
        System.out.printf("AuthType - %s%n", request.getAuthType());
        System.out.printf("QueryString - %s%n", request.getQueryString());
        System.out.printf("RequestedSessionId - %s%n", request.getRequestedSessionId());
        System.out.printf("RequestURI - %s%n", request.getRequestURI());
        System.out.printf("RequestURL - %s%n", request.getRequestURL());
        System.out.printf("RemoteUser - %s%n", request.getRemoteUser());
        System.out.printf("RemoteAddr - %s%n", request.getRemoteAddr());
        System.out.printf("RemoteHost - %s%n", request.getRemoteHost());
        System.out.printf("RemotePort - %s%n", request.getRemotePort());
        System.out.printf("Scheme - %s%n", request.getScheme());
        System.out.printf("ServletPath - %s%n", request.getServletPath());
        System.out.printf("ServerName - %s%n", request.getServerName());
        System.out.printf("ServerPort - %s%n", request.getServerPort());
        System.out.printf("LocalAddr - %s%n", request.getLocalAddr());
        System.out.printf("LocalName - %s%n", request.getLocalName());
        System.out.printf("LocalPort - %s%n", request.getLocalPort());

        System.out.println("\r\n -------parameters-------- \r\n");
        // parameters
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            System.out.println("" + paramName + " - " + values[0]);
        }

        System.out.println("\r\n -------cookies-------- \r\n");
        // cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " - " + cookie.getValue());
            }
        }

        System.out.println("\r\n -------headers-------- \r\n");
        // headers
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String value = request.getHeader(headerName);
            System.out.println("" + headerName + " - " + value);
        }

        System.out.println("\r\n --------------- \r\n");

        return "success";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(HttpServletRequest request) throws IOException, ServletException {
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            System.out.println(part.getName());
        }

        return "success";
    }
}
