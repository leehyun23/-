package com.hyunjoonlee.board.exception;
/**
 * 분류코드 에러처리
 */
public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(int tagNo) {
        super(" 분류코드가  " + tagNo + " 가 존재하지 않습니다.");
    }
}

