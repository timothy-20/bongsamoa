package com.timothy.bongsamoa.service;

import com.timothy.bongsamoa.mapper.TKBoardMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("boardDAOMyBatis")
public class TKBoardDAOMyBatis implements TKBoardDAO {
    @Resource
    private TKBoardMapper mapper;

    public TKBoardDAOMyBatis() {

    }

    @Override
    public int getBoardCount(TKBoardVO boardVO) throws Exception {
        return this.mapper.getBoardCount(boardVO);
    }

    @Override
    public TKBoardVO selectBoard(TKBoardVO boardVO) throws Exception {
        return this.mapper.selectBoard(boardVO);
    }

    @Override
    public List<TKBoardVO> selectAllBoards(TKBoardVO boardVO, int startRow, int limitRow) throws Exception {
        return this.mapper.selectAllBoards(boardVO, startRow, limitRow);
    }

    @Override
    public int insertBoard(TKBoardVO boardVO) throws Exception {
        return this.mapper.insertBoard(boardVO);
    }

    @Override
    public int updateBoard(TKBoardVO boardVO) throws Exception {
        return this.mapper.updateBoard(boardVO);
    }

    @Override
    public int deleteBoard(TKBoardVO boardVO) throws Exception {
        return this.mapper.deleteBoard(boardVO);
    }
}
