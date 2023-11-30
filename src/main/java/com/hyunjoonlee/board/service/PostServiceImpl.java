package com.hyunjoonlee.board.service;

import com.hyunjoonlee.board.dto.PostDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.entity.Post;
import com.hyunjoonlee.board.exception.PostNotFoundException;
import com.hyunjoonlee.board.repository.BoardDefRepository;
import com.hyunjoonlee.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Post ServiceImpl
 */
@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private  final BoardDefRepository boardDefRepository;

    @Override
    public int register(PostDto postDto) {
        BoardDef boardDef = boardDefRepository.findById(postDto.getBoardDefCd())
                .orElseThrow(()-> new NoSuchElementException());
        Post post = modelMapper.map(postDto, Post.class);
        post.setBoardDef(boardDef);
        Post savedPost = postRepository.save(post);

        int savedPostNo = savedPost.getPostNo();
        return savedPostNo;
    }

    @Override
    public PostDto readOne(int postNo) {

        Optional<Post> result = postRepository.findById(postNo);
        Post post = result.orElseThrow(() -> new PostNotFoundException(postNo));
        PostDto postDto = modelMapper.map(post, PostDto.class);

        return postDto;
    }

    @Override
    public int modify(PostDto postDto) {

        Optional<Post> result = postRepository.findById(postDto.getPostNo());

        Post post = result.orElseThrow(() -> new PostNotFoundException(postDto.getPostNo()));

        post.change(postDto.getPostSj(), postDto.getPostCn(), postDto.getRegstrId());

        return postRepository.save(post).getPostNo();
    }

    @Override
    public void remove(int postNo) {
        postRepository.deleteById(postNo);
    }

    @Override
    public List<Post> list() {
        return postRepository.findAll();
    }
}
