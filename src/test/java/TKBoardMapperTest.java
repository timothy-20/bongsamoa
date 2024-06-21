import com.timothy.bongsamoa.config.TKMapperContext;
import com.timothy.bongsamoa.mapper.TKBoardMapper;
import com.timothy.bongsamoa.service.TKBoardVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TKMapperContext.class })
public class TKBoardMapperTest {
    private final TKBoardMapper boardMapper;

    @Autowired
    public TKBoardMapperTest(TKBoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Test
    public void testGetBoardsCount() throws Exception {
        System.out.println("All boards count: " + boardMapper.getBoardsCount(null));

        TKBoardVO specificBoardVO1 = new TKBoardVO(0, null, "티모시 샬라메", null, null);
        System.out.println("Specific writer's board count: " + boardMapper.getBoardsCount(specificBoardVO1));
    }

    @Test
    public void testSelectAllBoards() throws Exception {
         ArrayList<TKBoardVO> boardVOList = new ArrayList<>(boardMapper.selectAllBoards());

         for (TKBoardVO boardVO : boardVOList) {
             System.out.println(boardVO.getTitle() + ", " + boardVO.getCreationDate().toString());
         }
    }

    @Test
    public void testSelectBoardById() throws Exception{
        TKBoardVO boardVO = boardMapper.selectBoardById(1);
        System.out.println(boardVO.getTitle() + ", " + boardVO.getCreationDate().toString());
    }

    @Test
    public void testSelectBoard() throws Exception {
        TKBoardVO specificBoardVO = new TKBoardVO(0, "듄 2", null, null, null);
        ArrayList<TKBoardVO> boardVOArrayList = new ArrayList<>(boardMapper.selectBoard(specificBoardVO));

        for (TKBoardVO boardVO : boardVOArrayList) {
            System.out.println(boardVO.getTitle() + ", " + boardVO.getWriter());
        }
    }

    @Test
    public void testInsertBoard() throws Exception {
        try {
            TKBoardVO newBoardVO = new TKBoardVO(4, "웡카", "티모시 샬라메", new Date(), "");
            Integer result = boardMapper.insertBoard(newBoardVO);
            System.out.println("Insert result number: " + result);

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testUpdateBoard() throws Exception {
        TKBoardVO newBoardVO = new TKBoardVO(4, "웡카", "티모시 샬라메", new Date(), "찰리와 초콜릿 공장의 프리퀄, 가족과 함께 보기 좋은 명작");
        Integer result = boardMapper.updateBoard(newBoardVO);
        System.out.println("Update result number: " + result);
    }

    @Test
    public void testDeleteBoard() throws Exception {
        Integer result = boardMapper.deleteBoard(4);
        System.out.println("Delete result number: " + result);
    }

    @Test
    public void test() throws Exception {

    }
}
