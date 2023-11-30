package com.hyunjoonlee.board.service;


import com.hyunjoonlee.board.dto.BoardDefDto;
import com.hyunjoonlee.board.dto.PostDto;
import com.hyunjoonlee.board.dto.TagDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.entity.Post;
import com.hyunjoonlee.board.repository.BoardDefRepository;
import com.hyunjoonlee.board.repository.PostRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Post Service Test Class
 */
@SpringBootTest
@Log4j2
@Transactional
public class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BoardDefRepository boardDefRepository;

    @Commit
    @Test   // 데이터 등록
    public void testRegister() {
        PostDto postDto = PostDto.builder()
                .boardDefCd("b001")//입력하실 Cd값 입력해주세여.(실제 있는값)
                .postSj("Sample Post")//입력하실 sj값 입력해주세여.
                .postCn("Sample Content")//입력하실 Cn값 입력해주세여.
                .regstrId("user123")//입력하실 regstrId값 입력해주세여.
                .regstrDt(LocalDateTime.now())
                .build();

        int savedPostNo = postService.register(postDto);
        Assertions.assertTrue(savedPostNo > 0);
    }

    @Test   // 단일 테스트 조회
    public void testReadOne() {
        int tagNo = 1;//조회할 NO값 을 입력해주세요.
        PostDto postDto = postService.readOne(tagNo);
        Assertions.assertNotNull(postDto);
        Assertions.assertEquals(tagNo, postDto.getPostNo());
    }


    @Commit
    @Test
    public void testModify() {
    int postNo = 3;//수정 하고자 하는 실제 No값 을 입력해 주세요.(실제 DB에 있는값)
    String newPostSj = "New PostSj";//수정 하고자 하는 새로운 값을 입력해 주세요.
    String newPostCn = "New PostCn";//수정 하고자 하는 새로운 값을 입력해 주세요.
    String newRegstrId = "New RegstrId";//수정 하고자 하는 새로운 값을 입력해 주세요.

    PostDto postDto = PostDto.builder()
            .postNo(postNo)
            .postSj(newPostSj)
            .postCn(newPostCn)
            .regstrId(newRegstrId)
            .build();
    int postNo2 = postService.modify(postDto);
        Assertions.assertEquals(postNo, postNo2);
    }

    @Commit
    @Test
    public void testRemove() {
        int postNo = 11; //삭제할 No값 을 입력해주세요.
        postService.remove(postNo);
        Optional<Post> deletedPost = postRepository.findById(postNo);
        Assertions.assertFalse(deletedPost.isPresent());
    }

    @Commit
    @Test
    public void testList() {
        List<Post> posts = postService.list();
        Assertions.assertNotNull(posts);
        Assertions.assertTrue(posts.size() > 0);
    }
}
