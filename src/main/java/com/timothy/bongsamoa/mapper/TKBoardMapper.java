package com.timothy.bongsamoa.mapper;

import com.timothy.bongsamoa.service.TKBoardVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TKBoardMapper {
    Integer getBoardsCount(TKBoardVO boardVO) throws Exception;
    List<TKBoardVO> selectAllBoards() throws Exception;
    TKBoardVO selectBoardById(Integer id) throws Exception;
    List<TKBoardVO> selectBoard(TKBoardVO boardVO) throws Exception;
    Integer insertBoard(TKBoardVO boardVO) throws Exception;
    Integer updateBoard(TKBoardVO boardVO) throws Exception;
    Integer deleteBoard(Integer id) throws Exception;
}
