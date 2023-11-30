package com.hyunjoonlee.board.service;

import com.hyunjoonlee.board.dto.PostDto;
import com.hyunjoonlee.board.entity.Post;

import java.util.List;
/**
 * Post Service
 */

public interface PostService {

    int register(PostDto postDto);
    PostDto readOne(int postNo);
    int modify(PostDto postDto);
    void remove(int postNo);
    List<Post> list();

}
