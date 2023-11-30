package com.hyunjoonlee.board.service;


import com.hyunjoonlee.board.dto.TagDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.entity.Tag;
import com.hyunjoonlee.board.repository.BoardDefRepository;
import com.hyunjoonlee.board.repository.BoardDefRepositoryTest;
import com.hyunjoonlee.board.repository.TagRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Tag Service Test Class
 */
@SpringBootTest
@Log4j2
@Transactional
public class TagServiceTest {

    @Autowired
    private TagService tagService;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private BoardDefRepository boardDefRepository;

    @Commit
    @Test   // 데이터 등록
    public void testRegister() {

        TagDto tagDto = TagDto.builder()
                .tag("Sample Tag")//입력하실 tag값 입력해주세여
                .boardCd("b001")//입력하실 Cd값 입력해주세여.(실제 있는값)
                .build();

        int savedTagNo = tagService.register(tagDto);
        Assertions.assertTrue(savedTagNo > 0);
    }

    @Test   // 단일 테스트 조회
    public void testReadOne() {
        int tagNo = 1; //조회할 NO값 을 입력해 주세요.
        TagDto tagDto = tagService.readOne(tagNo);
        Assertions.assertNotNull(tagDto);
        Assertions.assertEquals(tagNo, tagDto.getTagNo());
    }

    @Commit
    @Test
    public void testModify() {
        int tagNo = 1; //수정 하고자 하는 실제 No값 을 입력해 주세요.(실제 DB에 있는값)
        String newTag = "업데이트 Tag"; //수정 하고자 하는 새로운 값을 입력해 주세요.

        TagDto tagDto = TagDto.builder()
                .tagNo(tagNo)
                .tag(newTag)
                .build();
        int modifiedTagNo = tagService.modify(tagDto);
        Assertions.assertEquals(tagNo, modifiedTagNo);
    }


    @Commit
    @Test
    public void testRemove() {
        int tagNo = 9; //삭제할 No값 을 입력해주세요.
        tagService.remove(tagNo);

        Optional<Tag> deletedTag = tagRepository.findById(tagNo);
        Assertions.assertFalse(deletedTag.isPresent());
    }


    @Commit
    @Test
    public void testList() {
        List<Tag> tags = tagService.list();
        Assertions.assertNotNull(tags);
        Assertions.assertTrue(tags.size() > 0);
    }
}
