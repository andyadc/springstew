package com.andyadc.springboot.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * andy.an
 * 2020/4/2
 */
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/error", produces = {MediaType.TEXT_HTML_VALUE})
    public void handleError2Html(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Type", MediaType.TEXT_HTML_VALUE);
        PrintWriter writer = response.getWriter();

        Object statusCode = request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        String value = String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception == null ? "N/A" : exception.getMessage());

        writer.print(value);
        writer.flush();
    }

    @ResponseBody
    @RequestMapping(value = "/error", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object handleError2Json(HttpServletRequest request) {
        Object statusCode = request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        Map<String, Object> params = new HashMap<>(4);
        params.put("code", statusCode);
        params.put("exception", exception == null ? "N/A" : exception.getMessage());
        return params;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
