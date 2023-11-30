package com.hyunjoonlee.board.service;

import com.hyunjoonlee.board.dto.PostTagDto;
import com.hyunjoonlee.board.entity.PostTag;

import java.util.List;
/**
 * PostTag Service
 */
public interface PostTagService {
    int register(PostTagDto postTagDto);

    PostTagDto readOne(int postTagId);

    int remove(int postTagId);

    List<PostTag> list();

}
