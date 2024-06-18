package com.timothy.bongsamoa.controller;

import com.timothy.bongsamoa.service.TKBoardService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/board")
public class TKBoardController {
    @Resource(name = "boardService")
    private TKBoardService service;

    public String displayBoardList(HttpServletRequest request, Model model) throws Exception {
//        TKBoardVO boardVO = new TKBoardVO();


        return "TKBoardListPage";
    }
}
