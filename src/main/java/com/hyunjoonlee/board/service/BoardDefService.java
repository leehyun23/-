package com.hyunjoonlee.board.service;

import com.hyunjoonlee.board.dto.BoardDefDto;
import com.hyunjoonlee.board.entity.BoardDef;

import java.util.List;

/**
 * BoardDef Service
 */
public interface BoardDefService {
    String register(BoardDefDto boardDefDto);
    BoardDefDto readOne(String boardCd);
    String modify(String boardDefCd, BoardDefDto boardDefDto);
    void remove(String boardCd);

    List<BoardDef> list();

}
