package com.hyunjoonlee.board.exception;
/**
 * 분류코드 에러처리
 */
public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(int postNo) {
        super(" 분류코드가  " + postNo + " 가 존재하지 않습니다.");
    }
}
