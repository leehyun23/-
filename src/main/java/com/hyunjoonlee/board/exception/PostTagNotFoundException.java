package com.hyunjoonlee.board.exception;
/**
 * 분류코드 에러처리
 */
public class PostTagNotFoundException extends RuntimeException {
    public PostTagNotFoundException(int boardTagId) {
        super(" 분류코드가  " + boardTagId + " 가 존재하지 않습니다.");
    }
}
