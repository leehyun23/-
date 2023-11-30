package com.hyunjoonlee.board.service;

import com.hyunjoonlee.board.dto.PostTagDto;
import com.hyunjoonlee.board.entity.PostTag;
import com.hyunjoonlee.board.repository.PostTagRepository;
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
 * PostTag Service Test Class
 */
@SpringBootTest
@Log4j2
@Transactional
public class PostTagServiceTest {
    @Autowired
    private PostTagService postTagService;
    @Autowired
    private PostTagRepository postTagRepository;


    @Commit
    @Test   // 데이터 등록
    public void testRegister() {
        PostTagDto postTagDto = PostTagDto.builder()
                .postNo(2)//입력하실 Post No 입력해주세여.(실제 있는값)
                .boardDefCd("b001")//입력하실 boardDefCd 입력해주세여.(실제 있는값)
                .tagNo(2)//입력하실 tagNo 입력해주세여.(실제 있는값)
                .build();

        int savedPostTagId = postTagService.register(postTagDto);
        Assertions.assertTrue(savedPostTagId > 0);

    }

    @Test   // 단일 테스트 조회
    public void testReadOne() {
        int postTagId = 1; // 존재하는 조회할 PostTag ID 입력해 주세여
        PostTagDto postTagDto = postTagService.readOne(postTagId);
        Assertions.assertNotNull(postTagDto);
        Assertions.assertEquals(postTagId, postTagDto.getPostTagId());
    }

    @Commit
    @Test
    public void testRemove() {
        int postTagId = 8; // 존재하는 삭제할 PostTag ID 입력해주세요
        postTagService.remove(postTagId);
        Optional<PostTag> deletedPostTag = postTagRepository.findById(postTagId);
        Assertions.assertFalse(deletedPostTag.isPresent());
    }


    @Commit
    @Test
    public void testGetAllPostTags() {
        List<PostTag> postTags = postTagService.list();
        Assertions.assertNotNull(postTags);
        Assertions.assertTrue(postTags.size() > 0);
    }

}
