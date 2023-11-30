package com.hyunjoonlee.board.exception;

/**
 * 분류코드 에러처리
 */
public class BoardDefNotFoundException extends RuntimeException{
    public BoardDefNotFoundException(String boardCd) {
        super(" 분류코드가  " + boardCd + " 가 존재하지 않습니다.");
    }

}
