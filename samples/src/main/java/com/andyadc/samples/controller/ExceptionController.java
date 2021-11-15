package com.andyadc.samples.controller;

import com.andyadc.samples.exception.SamplesException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * https://reflectoring.io/spring-boot-exception-handling/
 * https://github.com/thombergs/code-examples/tree/master/spring-boot/exception-handling
 */
@RequestMapping("/exception")
@Controller
public class ExceptionController {

    @ResponseBody
    @RequestMapping("/runtime")
    public Object throwRuntimeException() {
        throw new RuntimeException("~RuntimeException~");
    }

    @ResponseBody
    @RequestMapping("/samples")
    public Object throwSamplesException() {
        throw new SamplesException("~SamplesException~");
    }
}
