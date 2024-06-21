package com.timothy.bongsamoa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
@RequestMapping(value = "/test")
public class TKTestController {
    @ResponseBody
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String logOutputTest() {
//        log.error("check save log...");

        return "done";
    }
}
