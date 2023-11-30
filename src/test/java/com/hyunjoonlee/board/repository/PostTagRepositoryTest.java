package com.hyunjoonlee.board.repository;


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
 * PostTag Repository Test Class
 */
@SpringBootTest
@Log4j2
@Transactional
public class PostTagRepositoryTest {

    @Autowired
    private BoardDefRepository boardDefRepository;
    @Autowired
    public PostRepository postRepository;
    @Autowired
    public TagRepository tagRepository;
    @Autowired
    public PostTagRepository postTagRepository;


    @Commit
    @Test // 데이터 등록
    public void testPostTagInsert() {
        BoardDef boardDef = boardDefRepository.findById("Test Cd...1")
                .orElse(null);
        Assertions.assertNotNull(boardDef);

        Post post = postRepository.findById(1)
                .orElse(null);
        Assertions.assertNotNull(post);

        Tag tag = tagRepository.findById(1)
                .orElse(null);
        Assertions.assertNotNull(tag);

        IntStream.rangeClosed(1, 10).forEach(i -> {

            PostTag postTag = PostTag.builder()
                    .post(post)
                    .boardDef(boardDef)
                    .tag(tag)
                    .build();

            PostTag result = postTagRepository.save(postTag);
            log.info("result : " + result.getPostTagId());
            Assertions.assertNotNull(result.getPostTagId());
        });
    }


    @Test // 단일 데이터 조회
    public void testPostTagSelect() {
        Optional<PostTag> result = postTagRepository.findById(1);
        log.info("PostTag with ID : " + result.orElse(null));
        if (result.isPresent()) {
            PostTag postTag = result.get();
            log.info("Selected Tag: " + postTag);
        } else {
            log.info("해당 태그가 존재하지 않습니다.");
        }
        long tagCount = tagRepository.count();
        Assertions.assertTrue(tagCount >= 0);
    }

    @Commit
    @Test //데이터 삭제
    public void testPostTagDelete() {
        postTagRepository.deleteById(9);
        Optional<PostTag> deletedPostTag = postTagRepository.findById(9);
        Assertions.assertTrue(deletedPostTag.isEmpty());
    }

    @Test  //데이터 모두 조회
    public void testPostTagsList() {
        List<PostTag> postTags = postTagRepository.findAll();
        Assertions.assertNotNull(postTags);
        Assertions.assertTrue(postTags.size() > 0);
    }

}