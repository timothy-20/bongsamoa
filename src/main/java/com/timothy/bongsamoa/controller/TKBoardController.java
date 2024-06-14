package com.timothy.bongsamoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TKBoardController {
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String display() {
        return "TKBoardPage";
    }
}
