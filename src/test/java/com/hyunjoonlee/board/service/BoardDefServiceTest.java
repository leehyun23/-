package com.hyunjoonlee.board.service;


import com.hyunjoonlee.board.dto.BoardDefDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.exception.BoardDefNotFoundException;
import com.hyunjoonlee.board.repository.BoardDefRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * BoardDef  테스트 클라스
 *
 */
@SpringBootTest
@Log4j2
@Transactional
public class BoardDefServiceTest {

    @Autowired
    private BoardDefService boardDefService;
    @Autowired
    private BoardDefRepository boardDefRepository;

    @Commit
    @Test   //데이터 등록
    public void testRegister() {

        BoardDefDto boardDef = BoardDefDto.builder()
                .boardCd("b001") //입력하실 Cd값 입력해주세여.
                .boardNm("고객센터")// 입력하실 Nm값 입력해주세여.
                .build();
        String savedBoardDefCd = boardDefService.register(boardDef);
        Assertions.assertTrue(savedBoardDefCd.equals("b001"));//입력한 Cd값이랑 일치해야 됨니다.
    }

    @Test   //단일 테스트 조회
    public void testReadOne() {

        String boardCd = "b001"; //조회할 cd값을 입력해주세요.

        BoardDefDto boardDefDto = BoardDefDto.builder()
                .boardCd(boardCd)
                .build();
        BoardDefDto readBoardDefDto = boardDefService.readOne(boardCd);
        Assertions.assertEquals(boardDefDto.getBoardCd(), readBoardDefDto.getBoardCd());
    }



    @Commit
    @Test
    public void testModify() {
        // 테스트할 게시판 코드
        String boardCd = "Test Cd...8";// 수정하고자하는 boardDefCd값을 입력해주세요.(실제DB에 있는값)
        String newBoardName = "New Board Name";//수정 하고자하는 새로운 값을 입력해주세요(수정할 값)

        BoardDefDto boardDefDto = BoardDefDto.builder()
                .boardCd(boardCd)
                .boardNm(newBoardName)
                .build();
        String boardDefNm = boardDefService.modify(boardCd, boardDefDto);

        Assertions.assertTrue(boardDefNm.equals(newBoardName));

    }



    @Commit
    @Test
    public void testRemove() {
        String boardCd = "Test Cd...9";
        boardDefService.remove(boardCd);

        Optional<BoardDef> deletedBoard = boardDefRepository.findById(boardCd);
        Assertions.assertFalse(deletedBoard.isPresent());
    }

    @Commit
    @Test
    public void testList() {

        List<BoardDef> boardDefs = boardDefService.list();

        Assertions.assertNotNull(boardDefs);
        Assertions.assertTrue(boardDefs.size() > 0);
    }


}
