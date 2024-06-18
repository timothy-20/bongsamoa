package com.timothy.bongsamoa.mapper;

import com.timothy.bongsamoa.service.TKBoardVO;
import org.apache.ibatis.annotations.Select;

//import java.util.List;

public interface TKBoardMapper {
    @Select("SELECT * FROM board WHERE 1 = 1 AND id == #{boardVO.id}")
    public TKBoardVO selectBoard(TKBoardVO boardVO) throws Exception;

//    int getBoardCount(TKBoardVO boardVO) throws Exception;
//    TKBoardVO selectBoard(TKBoardVO boardVO) throws Exception;
//    List<TKBoardVO> selectAllBoards(TKBoardVO boardVO, @Param("startRow") int startRow, @Param("limitRow") int limitRow) throws Exception;
//    int insertBoard(TKBoardVO boardVO) throws Exception;
//    int updateBoard(TKBoardVO boardVO) throws Exception;
//    int deleteBoard(TKBoardVO boardVO) throws Exception;
}
