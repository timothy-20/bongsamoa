package com.timothy.bongsamoa.controller;

import com.timothy.bongsamoa.entity.TKBoard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TKBoardController {
    public TKBoardController() {

    }

    @RequestMapping("/getBoardList.do")
    public String getBoardList(Model model) {
        List<TKBoard> boardList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            TKBoard board = new TKBoard(i, "timothy");
            board.setContent(String.format("%d", i));

            boardList.add(board);
        }

        model.addAttribute("boardList", boardList);

        return "getBoardList";
    }
}
