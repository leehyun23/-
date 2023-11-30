package com.hyunjoonlee.board.repository;

import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.entity.Tag;
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
 * Tag Repository Test Class
 */
@SpringBootTest
@Log4j2
@Transactional
public class TagRepositoryTest {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private BoardDefRepository boardDefRepository;

    @Commit
    @Test   //데이터 등록
    public void testTagInsert() {
        BoardDef boardDef = boardDefRepository.findById("Test Cd...1")
                .orElse(null);
        Assertions.assertNotNull(boardDef);

        IntStream.rangeClosed(1, 10).forEach(i -> {

            Tag tag = Tag.builder()
                    .tag("Test..." + i)
                    .boardDef(boardDef)
                    .build();


            Tag result = tagRepository.save(tag);
            log.info("result : " + result.getTagNo());
            Assertions.assertNotNull(result.getTagNo());
        });
    }

    @Test   //단일 데이터 조회
    public void testTagSelect() {
        int tagNo = 1;
        Optional<Tag> result = tagRepository.findById(tagNo);

        if (result.isPresent()) {
            Tag tag = result.get();
            log.info("Selected Tag: " + tag);
        } else {
            log.info("해당 태그가 존재하지 않습니다.");
        }
        long tagCount = tagRepository.count();
        Assertions.assertTrue(tagCount >= 0);
    }

    @Commit
    @Test   //데이터  업데이트
    public void testTagUpdate() {
        Optional<Tag> result = tagRepository.findById(2);

        Tag tag = result.orElseThrow();
        tag.change(
                "업데이트..Tast Tag");

        tagRepository.save(tag);

        Optional<Tag> updatedTag = tagRepository.findById(1);
        Assertions.assertTrue(updatedTag.isPresent());
    }

    @Commit
    @Test   //데이터 삭제
    public void testTagDelete() {
        tagRepository.deleteById(10);

        Optional<Tag> deletedTag = tagRepository.findById(10);
        Assertions.assertTrue(deletedTag.isEmpty());
    }

    @Test   //데이터 모두 조회
    public void testTagList() {
        List<Tag> tags = tagRepository.findAll();
        boardDefRepository.count();

        Assertions.assertNotNull(tags);
        Assertions.assertTrue(tags.size() > 0);
    }

}
