package com.hyunjoonlee.board.repository;

import com.hyunjoonlee.board.dto.PostDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.entity.Post;
import com.hyunjoonlee.board.entity.PostTag;
import com.hyunjoonlee.board.entity.Tag;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Post Repository Test Class
 */
@SpringBootTest
@Log4j2
@Transactional
public class PostRepositoryTest {

    @Autowired
    public PostRepository postRepository;
    @Autowired
    private BoardDefRepository boardDefRepository;


    @Commit
    @Test   //데이터 등록
    public void testPostInsert() {
        BoardDef boardDef = boardDefRepository.findById("Test Cd...1")
                .orElse(null);
        Assertions.assertNotNull(boardDef);

        IntStream.rangeClosed(1, 10).forEach(i -> {

            Post post = Post.builder()
                    .boardDef(boardDef)
                    .postSj("Test postSj..." + i)
                    .postCn("Test postCn..." + i)
                    .regstrId("Test regstrId..." + i)
                    .regstrDt(LocalDateTime.now())
                    .build();

            Post result = postRepository.save(post);
            log.info("Saved Post: " + result.getPostNo());
        });

        long postCount = postRepository.count();
        Assertions.assertTrue(postCount >= 0);
    }

    @Test // 단일 데이터 조회
    public void testPostSelect() {
        int postNo = 1;
        Optional<Post> result = postRepository.findById(postNo);

        if (result.isPresent()) {
            Post post = result.get();
            log.info("Selected Post: " + post);
        } else {
            log.info("해당 게시물이 존재하지 않습니다.");
        }

        Optional<Post> updatedpost = postRepository.findById(1);
        Assertions.assertTrue(updatedpost.isPresent());
    }

    @Commit
    @Test   //데이터 업데이트
    public void testPostUpdate() {
        Optional<Post> result = postRepository.findById(2);

        Post post = result.orElseThrow();
        post.change(
                "업데이트..Tast postSj",
                "업데이트..Tast postCn",
                "업데이트..Tast regstrId");

        postRepository.save(post);

        Optional<Post> updatedPost = postRepository.findById(1);
        Assertions.assertTrue(updatedPost.isPresent());
    }

    @Commit
    @Test    //데이터 삭제
    public void testPostDelete() {
        postRepository.deleteById(10);

        Optional<Post> deletedPost = postRepository.findById(10);
        Assertions.assertTrue(deletedPost.isEmpty());
    }

    @Test   //데이터 모두 조회
    public void testPostList() {
        List<Post> post = postRepository.findAll();
        boardDefRepository.count();

        Assertions.assertNotNull(post);
        Assertions.assertTrue(post.size() > 0);
    }
}
