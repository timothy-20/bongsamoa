package com.timothy.bongsamoa.service;

import java.util.List;

public interface TKBoardDAO {
    int getBoardCount(TKBoardVO boardVO) throws Exception;
    TKBoardVO selectBoard(TKBoardVO boardVO) throws Exception;
    List<TKBoardVO> selectAllBoards(TKBoardVO boardVO, int startRow, int limitRow) throws Exception;
    int insertBoard(TKBoardVO boardVO) throws Exception;
    int updateBoard(TKBoardVO boardVO) throws Exception;
    int deleteBoard(TKBoardVO boardVO) throws Exception;
}
