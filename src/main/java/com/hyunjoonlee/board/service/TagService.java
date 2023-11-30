package com.hyunjoonlee.board.service;

import com.hyunjoonlee.board.dto.BoardDefDto;
import com.hyunjoonlee.board.dto.TagDto;
import com.hyunjoonlee.board.entity.Tag;

import java.util.List;

/**
 * Tag Service
 */

public interface TagService {
    int register(TagDto tagDto);
    TagDto readOne(int tagNo);
    int modify(TagDto tagDto);
    int remove(int tagNo);
    List<Tag> list();

}
