package com.hyunjoonlee.board.repository;


import com.hyunjoonlee.board.entity.BoardDef;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * BoardDef Repository Test Class
 *
 */
@SpringBootTest
@Log4j2
@Transactional
public class BoardDefRepositoryTest {


    @Autowired
    public BoardDefRepository boardDefRepository;

    @Commit
    @Test   //데이터 등록
    public void testBoardDefInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            BoardDef boardDef = BoardDef.builder()
                    .boardCd("Test Cd..." + i)
                    .boardNm("Test Nm..." + i)
                    .build();

            BoardDef result = boardDefRepository.save(boardDef);
            log.info("result :" + result.getBoardCd());

        });
        long boardDefCount = boardDefRepository.count();
        Assertions.assertTrue(boardDefCount >= 0);
    }// end

    @Test   //단일 데이터 조회
    public void testBoardDefSelect() {
        String boardCd = "Test Cd..." + "2"; //찾을려는 Cd 넘버

        Optional<BoardDef> result = boardDefRepository.findById(boardCd);

        //true
        if (result.isPresent()) {
            BoardDef boardDef = result.get();
            log.info("boardDef :" + boardDef);
        //false
        } else {
            log.info("해당 게시물이 존재하지 않습니다.");
        }
        long boardDefCount = boardDefRepository.count();
        Assertions.assertTrue(boardDefCount >= 0);
    }


    @Commit
    @Test   //데이터 업데이트
    public void  testBoardDefUpdate() {
        String boardCd = "Test Cd..." + "2"; //업데이트할 Cd 넘버
        Optional<BoardDef> result = boardDefRepository.findById(boardCd);

        BoardDef boardDef = result.orElseThrow();

        boardDef.change( "업데이트..Tast Nm2.2");
        log.info("Nm :" + boardDef);
        boardDefRepository.save(boardDef);
        log.info("Nm :" + boardDef);

        Optional<BoardDef> updatedBoardDef = boardDefRepository.findById(boardCd);
        Assertions.assertTrue(
                updatedBoardDef.get().getBoardNm().equals("업데이트..Tast Nm2.2"));
    }

    @Commit
    @Test   //데이터 삭제
    public void testBoardDefDelete() {
        String boardCd = "Test Cd..." + "7"; //삭제를 하려는 Cd 넘버
        boardDefRepository.deleteById(boardCd);

        Optional<BoardDef> deletedBoardDef = boardDefRepository.findById(boardCd);
        Assertions.assertTrue(
                deletedBoardDef.isEmpty());
    }

    @Test   //데이터 모두 조회
    public void testBoardDefList() {
        // BoardDef 엔터티 목록 가져오기
        List<BoardDef> boardDefs = boardDefRepository.findAll();
        boardDefRepository.count();

        Assertions.assertNotNull(boardDefs);
        Assertions.assertTrue(boardDefs.size() > 0);
    }
}
