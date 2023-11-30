package com.hyunjoonlee.board.service;

import com.hyunjoonlee.board.dto.BoardDefDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.exception.BoardDefNotFoundException;
import com.hyunjoonlee.board.repository.BoardDefRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * BoardDef ServiceImpl
 */

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardDefServiceImpl implements BoardDefService{

    private final ModelMapper modelMapper;
    private final BoardDefRepository boardDefRepository;

    /**
     * 서비스 메소드
     * @param boardDefDto
     * @return
     */
    @Override
    public String register(BoardDefDto boardDefDto){

        BoardDef boardDef = modelMapper.map(boardDefDto, BoardDef.class);

        boardDef = boardDefRepository.save(boardDef);

        String boardCd = boardDef.getBoardCd();

        return boardCd;
    }

    /**
     * 상세 서비스 메소드
     * @param boardCd
     * @return
     */
    @Override
    public BoardDefDto readOne(String boardCd){

        Optional<BoardDef> result = boardDefRepository.findById(boardCd);

        BoardDef boardDef = result.orElseThrow(() ->
                new BoardDefNotFoundException(boardCd));
        BoardDefDto boardDefDto = modelMapper.map(boardDef, BoardDefDto.class);

        return boardDefDto;
    }

    /**
     * 수정 서비스 메소드
     * @param boardDefDto
     */
    @Override
    public String modify(String boardDefCd, BoardDefDto boardDefDto) {

        Optional<BoardDef> result = boardDefRepository.findById(boardDefCd);

        BoardDef boardDef = result.orElseThrow();

        boardDef.change(boardDefDto.getBoardNm());

        String boardDefNm = boardDefRepository.save(boardDef).getBoardNm();

        return boardDefNm;
    }

    @Override
    public void remove(String boardCd) {
         boardDefRepository.deleteById(boardCd);
    }

    @Override
    public List<BoardDef> list() {
        return boardDefRepository.findAll();
    }

}
