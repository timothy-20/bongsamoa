package com.timothy.bongsamoa.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("boardService")
@Transactional
public class TKBoardService {
    @Resource(name = "boardDAOMyBatis")
    TKBoardDAOMyBatis boardDAOMyBatis;

    public int getBoardCount(TKBoardVO boardVO) throws Exception {
        return this.boardDAOMyBatis.getBoardCount(boardVO);
    }

    public TKBoardVO selectBoard(TKBoardVO boardVO) throws Exception {
        return this.boardDAOMyBatis.selectBoard(boardVO);
    }

    public List<TKBoardVO> selectAllBoards(TKBoardVO boardVO, int startRow, int limitRow) throws Exception {
        return this.boardDAOMyBatis.selectAllBoards(boardVO, startRow, limitRow);
    }

    public int insertBoard(TKBoardVO boardVO) throws Exception {
        return this.boardDAOMyBatis.insertBoard(boardVO);
    }

    public int updateBoard(TKBoardVO boardVO) throws Exception {
        return this.boardDAOMyBatis.updateBoard(boardVO);
    }

    public int deleteBoard(TKBoardVO boardVO) throws Exception {
        return this.boardDAOMyBatis.deleteBoard(boardVO);
    }
}
