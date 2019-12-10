package com.andyadc.springboot.controller;

import com.andyadc.springboot.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * andy.an
 * 2019/12/10
 */
@Controller
public class EchoController {

    @ResponseBody
    @RequestMapping(value = "echo")
    public String echo(HttpServletRequest request, @RequestBody String body) {
        System.out.println("\r\n>>>>>>>>>>>>>>>>>>>>>>>\r\n");
        System.out.println(ServletUtil.getReqParams(request));
        System.out.println("\r\n");
        System.out.println(body);
        System.out.println("\r\n<<<<<<<<<<<<<<<<<<<<<<<\r\n");
        return "success";
    }
}
