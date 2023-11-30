package com.hyunjoonlee.board.service;

import com.hyunjoonlee.board.dto.PostTagDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.entity.Post;
import com.hyunjoonlee.board.entity.PostTag;
import com.hyunjoonlee.board.entity.Tag;
import com.hyunjoonlee.board.exception.PostTagNotFoundException;
import com.hyunjoonlee.board.repository.BoardDefRepository;
import com.hyunjoonlee.board.repository.PostRepository;
import com.hyunjoonlee.board.repository.PostTagRepository;
import com.hyunjoonlee.board.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * PostTag ServiceImpl
 */
@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PostTagServiceImpl implements PostTagService {

    private final ModelMapper modelMapper;
    private final PostTagRepository postTagRepository;
    private final BoardDefRepository boardDefRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    @Override
    public int register(PostTagDto postTagDto) {
        BoardDef boardDef = boardDefRepository.findById(postTagDto.getBoardDefCd())
                .orElseThrow(()-> new NoSuchElementException());
        Post post = postRepository.findById(postTagDto.getPostNo())
                .orElseThrow(()-> new NoSuchElementException());
        Tag tag = tagRepository.findById(postTagDto.getTagNo())
                .orElseThrow(()-> new NoSuchElementException());

        PostTag postTag = modelMapper.map(postTagDto, PostTag.class);
        postTag.setBoardDef(boardDef);
        postTag.setPost(post);
        postTag.setTag(tag);
        PostTag savedPostTag = postTagRepository.save(postTag);

        int savedPostTagId = savedPostTag.getPostTagId();
        return savedPostTagId;
    }

    @Override
    public PostTagDto readOne(int postTagId) {
        Optional<PostTag> result = postTagRepository.findById(postTagId);
        PostTag postTag = result.orElseThrow(() -> new PostTagNotFoundException(postTagId));
        return modelMapper.map(postTag, PostTagDto.class);
    }

    @Override
    public int remove(int postTagId) {
        postTagRepository.deleteById(postTagId);
        return postTagId;
    }

    @Override
    public List<PostTag> list() {
        return postTagRepository.findAll();
    }
}

