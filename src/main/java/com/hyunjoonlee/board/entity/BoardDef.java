package com.hyunjoonlee.board.entity;

import lombok.*;

import javax.persistence.*;

/**
 * BoardDef Entity Class
 */

@Getter @Setter
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board_def")
public class BoardDef {

    //게시판(분류)코드
    @Id
    @Column(name = "board_cd")
    private String boardCd;

    //게시판(분류)이름
    @Column(name = "board_nm")
    private String boardNm;

    public  void change(String boardNm) {
        this.boardNm = boardNm;
    }
}
